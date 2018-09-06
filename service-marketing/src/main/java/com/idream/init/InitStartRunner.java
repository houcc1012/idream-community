package com.idream.init;

import com.idream.basic.BasicService;
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
        //初始化抽奖奖品缓存信息
//		basicService.initLotteryAwardCache();
        //初始化兑奖奖品信息
//		basicService.initConvertAward();
//		//初始化现场开奖活动奖品
//		basicService.initSceneOpenAward();
    }
}
