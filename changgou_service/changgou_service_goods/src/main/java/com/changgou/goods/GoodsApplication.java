package com.changgou.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ccwu
 * @date 2021/10/21 17:11
 */

@SpringBootApplication
@EnableEurekaClient      //声明当前的工程是Eureka客户端
@MapperScan(basePackages = {"com.changgou.goods.dao"})    //当前工程需要操作数据库，导入通用mapper,即tk.mybatis。用于扫描Mapper接口
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class);
    }
}
