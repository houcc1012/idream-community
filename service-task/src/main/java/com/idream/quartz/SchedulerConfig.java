package com.idream.quartz;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;


/**
 * @Author: hejiang
 * @Description: quartz配置
 * @Date: 10:40 2018/5/11
 */
@Configuration
public class SchedulerConfig {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomJobFactory customJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        try {
            factory.setApplicationContextSchedulerContextKey("applicationContextKey");
            factory.setDataSource(dataSource);
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            factory.setConfigLocation(resolver.getResource("classpath:/application.properties"));
            factory.setJobFactory(customJobFactory);
        } catch (Exception e) {
            logger.error("创建 schedulerFactoryBean error", e);
        }
        return factory;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

}
