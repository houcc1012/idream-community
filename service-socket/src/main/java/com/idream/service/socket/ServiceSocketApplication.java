package com.idream.service.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
//@EnableDiscoveryClient
public class ServiceSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSocketApplication.class, args);
    }

    /**
     * 注册webSocket exporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
