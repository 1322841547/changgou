package com.changgou.canal.listener;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.changgou.canal.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author ccwu
 * @date 2021/11/4 16:23
 * <p>
 *     实现了ApplicationRunner接口后，启动APP时会首先初始化该类
 *     rabbitmq生产者， 消费者 changgou_service/changgou_service_business： com.ccwu.listener.AdListener
 *
 */

@PropertySource("classpath:canal.properties")
@Component

public class BusinessListener implements ApplicationRunner {

    @Value("${canal.host}")
    private String host;
    @Value("${canal.port}")
    private int port;
    @Value("${canal.instance}")
    private String instance;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 创建链接
        CanalConnector conn = CanalConnectors.newSingleConnector(new InetSocketAddress(host,
                port), instance, "", "");

        try {
            conn.connect();
            //建立连接
            //cnn.subscribe(".*\\..*"); //所有库所有表
            // 订阅changgou_business库中的tb_ad表
            conn.subscribe("changgou_business.tb_ad");

            conn.rollback();                      //回滚到未进行ack的地方
            int emptyCount = 0;    //失败请求次数
            int totalEmptyCount = 120;            //请求失败总数

            //获取数据100条数据后提交确认，或者连续失败120次后结束
            while (emptyCount < totalEmptyCount) {
                Message message = conn.getWithoutAck(1000);    // 获取指定数量的数据
                long id = message.getId();
                int size = message.getEntries().size();
                //获取到数据：
                if (id != -1 && size > 0) {
                    emptyCount = 0;
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    analysis(message.getEntries());   //数据解析
                } else {
                    emptyCount++;  //失败次数加1
                    System.out.println("没获取到empty count : " + emptyCount);
                    try {
                        Thread.sleep(1000);   //延迟1s后再获取
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                conn.ack(id);  //每获取一条id，提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }


        } finally {
            conn.disconnect();
        }

    }

    /**
     * 数据解析
     *
     * @param entrys
     */
    private void analysis(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else {
//                    System.out.println("-----------------------------------------&gt; before");
//                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-----------------------------------------&gt; after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    /**
     * 打印输出name、value
     * @param columns
     */
    private void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());

            if ("position".equals(column.getName())){
                //发送消息
                rabbitTemplate.convertAndSend("", RabbitMQConfig.AD_UPDATE_QUEUE,column.getValue());
            }
        }
    }
}