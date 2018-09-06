package com.idream.rabbit;

import com.idream.commons.lib.base.RabbitMqConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;


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
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }


    @Bean
    public DirectExchange awardExchange() {
        return new DirectExchange(RabbitMqConstants.AWARD_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange dlAwardDirectExchange() {
        return new DirectExchange(RabbitMqConstants.DL_AWARD_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Queue awardQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put(RabbitMqConstants.DEAD_LETTER_EXCHANGE, RabbitMqConstants.DL_AWARD_DIRECT_EXCHANGE); //声明 死信交换机
        args.put(RabbitMqConstants.DEAD_LETTER_ROUTING_KEY, RabbitMqConstants.AWARD_ROUTING_KEY);//声明 死信路由键
        return QueueBuilder.durable(RabbitMqConstants.AWARD_QUEUE).withArguments(args).build();
    }

    @Bean
    public Queue redirectAwardQueue() {
        return QueueBuilder.durable(RabbitMqConstants.DL_AWARD_QUEUE).build();
    }

    @Bean
    public Binding awardBinding() {
        return BindingBuilder.bind(awardQueue()).to(awardExchange()).with(RabbitMqConstants.AWARD_ROUTING_KEY);
    }

    @Bean
    public Binding dlAwardBinding() {
        return BindingBuilder.bind(redirectAwardQueue()).to(dlAwardDirectExchange()).with(RabbitMqConstants.AWARD_ROUTING_KEY);
    }

    @Bean(name = "awardListenContainer")
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
