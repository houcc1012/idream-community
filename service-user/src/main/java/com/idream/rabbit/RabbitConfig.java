package com.idream.rabbit;

import com.idream.commons.lib.base.RabbitMqConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class RabbitConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitmqPort;

    @Value("${spring.rabbitmq.username}")
    private String userName;

    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     * @Author: hejiang
     * @Description: 配置链接信息
     * @Date: 9:46 2018/5/17
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHost, rabbitmqPort);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(RabbitMqConstants.VIRTUAL_HOST);
        //设置发送消息确认
        connectionFactory.setPublisherConfirms(true);
        //修改信道连接数 防止并发过大丢消息问题
        connectionFactory.setChannelCacheSize(25);
        return connectionFactory;
    }

    /**
     * @Author: hejiang
     * @Description: 初始化 rabbitMq template
     * @Date: 9:46 2018/5/17
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //非单例模式
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean(name = "wangyiImListenContainer")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMaxConcurrentConsumers(2);
        factory.setConcurrentConsumers(2);
        factory.setPrefetchCount(10);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

}
