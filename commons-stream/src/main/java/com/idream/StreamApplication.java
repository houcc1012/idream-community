package com.idream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;


/**
 * @author charles.wei
 */
@MapperScan(basePackages = "com.idream.commons.lib.mapper")
@SpringCloudApplication
@EnableHystrixDashboard
@EnableTurbine
public class StreamApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StreamApplication.class, args);
    }
}
