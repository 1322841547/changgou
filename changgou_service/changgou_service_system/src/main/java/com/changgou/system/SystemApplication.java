package com.changgou.system;

import com.netflix.discovery.EurekaNamespace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ccwu
 * @date 2021/10/24 21:08
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.system.dao"})    //当前工程需要操作数据库，导入通用mapper,即tk.mybatis。用于扫描Mapper接口
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
