package com.changgou.listener;

import com.changgou.service.ESManagerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ccwu
 * @date 2021/11/18 11:08
 */

@Component
public class GoodsDelListener {

    @Autowired
    private ESManagerService esManagerService;

    @RabbitListener(queues = "search_add_queue")
    public void receiveMessage(String spuId){
        System.out.println("接收到的消息为:   " + spuId);

        esManagerService.deleteBySpuId(spuId);

    }
}