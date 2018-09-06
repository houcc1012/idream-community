package com.idream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@MapperScan(basePackages = "com.idream.commons.lib.mapper")
@EnableFeignClients //开启fegin
public class ServiceMarketingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMarketingApplication.class, args);
    }

}
