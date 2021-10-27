package com.changgou.goods;

import com.changgou.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ccwu
 * @date 2021/10/21 17:11
 */

@SpringBootApplication
@EnableEurekaClient      //声明当前的工程是Eureka客户端
@MapperScan(basePackages = {"com.changgou.goods.dao"})    //当前工程需要操作数据库，导入通用mapper,即tk.mybatis。用于扫描Mapper接口
public class GoodsApplication {

    @Value("${workerId}")
    private Integer workerId;
    @Value("${datacenterId}")
    private Integer datacenterId;

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class);
    }

    //配置分布式ID生成器

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(workerId, datacenterId);
    }
}
