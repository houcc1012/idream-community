package com.idream.rabbitmq;

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

    /**
     * @Author: hejiang
     * @Description: 配置邻里圈消息交换机
     * @Date: 9:48 2018/5/17
     */
    @Bean
    public DirectExchange communityLifeDirectExchange() {
        return new DirectExchange(RabbitMqConstants.COMMUNITY_LIFE_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange dlCommunityLifeDirectExchange() {
        return new DirectExchange(RabbitMqConstants.DL_COMMUNITY_LIFE_DIRECT_EXCHANGE, true, false);
    }

    /**
     * @Author: hejiang
     * @Description: 配置邻里圈消息队列
     * @Date: 9:48 2018/5/17
     */
    @Bean
    public Queue communityLifeQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put(RabbitMqConstants.DEAD_LETTER_EXCHANGE, RabbitMqConstants.DL_COMMUNITY_LIFE_DIRECT_EXCHANGE); //声明 死信交换机
        args.put(RabbitMqConstants.DEAD_LETTER_ROUTING_KEY, RabbitMqConstants.COMMUNITY_LIFE_ROUTING_KEY);//声明 死信路由键
        return QueueBuilder.durable(RabbitMqConstants.COMMUNITY_LIFE_TIME_LINE_QUEUE).withArguments(args).build();
    }

    /**
     * @Author: hejiang
     * @Description: 配置邻里圈死信转发队列
     * @Date: 10:24 2018/5/17
     */
    @Bean
    public Queue redirectCommunityLifeQueue() {
        return QueueBuilder.durable(RabbitMqConstants.DL_COMMUNITY_LIFE_TIME_LINE_QUEUE).build();
    }

    /**
     * @Author: hejiang
     * @Description: 绑定邻里圈队列和交换机 指定路由键
     * @Date: 10:25 2018/5/17
     */
    @Bean
    public Binding communtiyLifeBinding() {
        return BindingBuilder.bind(communityLifeQueue()).to(communityLifeDirectExchange()).with(RabbitMqConstants.COMMUNITY_LIFE_ROUTING_KEY);
    }

    /**
     * @Author: hejiang
     * @Description: 绑定邻里圈死信队列和交换机 指定路由键
     * @Date: 10:25 2018/5/17
     */
    @Bean
    public Binding dlCommuntiyLifeBinding() {
        return BindingBuilder.bind(redirectCommunityLifeQueue()).to(dlCommunityLifeDirectExchange()).with(RabbitMqConstants.COMMUNITY_LIFE_ROUTING_KEY);
    }

    @Bean(name = "communityLifeListenContainer")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMaxConcurrentConsumers(2);
        factory.setConcurrentConsumers(2);
        factory.setPrefetchCount(10);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    /*----------------------------------------------------------网易IM-------------------------------------------------------------*/

    /**
     * @Author: hejiang
     * @Description: 配置IM消息交换机
     * @Date: 9:48 2018/5/17
     */
    @Bean
    public DirectExchange imDirectExchange() {
        return new DirectExchange(RabbitMqConstants.IM_DIRECT_EXCHANGE, true, false);
    }

    /**
     * @Author: hejiang
     * @Description: 配置IM消息转发交换机
     * @Date: 9:48 2018/5/17
     */
    @Bean
    public DirectExchange dlImDirectExchange() {
        return new DirectExchange(RabbitMqConstants.DL_IM_DIRECT_EXCHANGE, true, false);
    }

    /**
     * @Author: hejiang
     * @Description: 配置IM消息队列
     * @Date: 9:48 2018/5/17
     */
    @Bean
    public Queue imQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put(RabbitMqConstants.DEAD_LETTER_EXCHANGE, RabbitMqConstants.DL_IM_DIRECT_EXCHANGE); //声明 死信交换机
        args.put(RabbitMqConstants.DEAD_LETTER_ROUTING_KEY, RabbitMqConstants.IM_ROUTING_KEY);//声明 死信路由键
        return QueueBuilder.durable(RabbitMqConstants.IM_QUEUE).withArguments(args).build();
    }

    /**
     * @Author: hejiang
     * @Description: 配置IM死信转发队列
     * @Date: 10:24 2018/5/17
     */
    @Bean
    public Queue redirectImQueue() {
        return QueueBuilder.durable(RabbitMqConstants.DL_IM_QUEUE).build();
    }

    /**
     * @Author: hejiang
     * @Description: 绑定IM队列和交换机 指定路由键
     * @Date: 10:25 2018/5/17
     */
    @Bean
    public Binding imBinding() {
        return BindingBuilder.bind(imQueue()).to(imDirectExchange()).with(RabbitMqConstants.IM_ROUTING_KEY);
    }

    /**
     * @Author: hejiang
     * @Description: 绑定IM死信队列和交换机 指定路由键
     * @Date: 10:25 2018/5/17
     */
    @Bean
    public Binding dlImBinding() {
        return BindingBuilder.bind(redirectImQueue()).to(dlImDirectExchange()).with(RabbitMqConstants.IM_ROUTING_KEY);
    }

}
