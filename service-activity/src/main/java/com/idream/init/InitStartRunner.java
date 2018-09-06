package com.idream.init;

import com.idream.base.BasicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * spring boot 初始化数据
 */
@Component
public class InitStartRunner implements CommandLineRunner {

    @Resource
    private BasicService basicService;

    @Override
    public void run(String... args) throws Exception {
        //初始化敏感词数据
        basicService.initSensitiveWord();
    }
}
