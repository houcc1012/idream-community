package com.idream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启fegin
public class ServiceTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTaskApplication.class, args);
    }
}
