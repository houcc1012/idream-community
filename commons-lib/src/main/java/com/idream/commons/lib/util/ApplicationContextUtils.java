package com.idream.commons.lib.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clz) {
        return applicationContext.getBean(clz);
    }

    public static <T> T getBean(String name, Class<T> clz) {
        return applicationContext.getBean(name, clz);
    }

    @Override
    public void setApplicationContext(
            final ApplicationContext applicationContext) {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
