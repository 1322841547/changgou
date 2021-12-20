package com.ccwu.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ccwu
 * @date 2021/11/24 18:13
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.changgou.goods.feign")
public class PageApplication {

    public static void main(String[] args) {

        SpringApplication.run(PageApplication.class, args);
    }
}
