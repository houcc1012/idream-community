package com.idream.service.marketing;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.marketing.IntegrationDrawDto;
import com.idream.commons.lib.dto.marketing.IntegrationDrawParams;
import com.idream.commons.lib.dto.marketing.OpenLotteryAwardDto;
import com.idream.commons.lib.dto.marketing.OpenLotteryAwardParams;
import com.idream.commons.lib.dto.marketing.RegionPollParams;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.rabbit.RabbitSendService;
import com.idream.rabbit.SocketSendService;
import com.idream.service.IntegrationService;
import com.idream.service.LotteryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceMarketingApplicationTests {

    @Resource
    private IntegrationService integrationService;

    @Resource
    private LotteryService lotteryService;

    @Resource
    private SocketSendService socketSendService;

    @Resource
    private RabbitSendService rabbitSendService;

    @Test
    public void testRabbit() throws InterruptedException {
        rabbitSendService.sendIntegrationAward("测试");
        Thread.sleep(123450);
    }

    @Test
    public void selectPool() {
        RegionPollParams param = new RegionPollParams();
        param.setAuthUserId(120);
        param.setCityCode("310100");
        param.setLatitude(new BigDecimal("30.265499"));
        param.setLongitude(new BigDecimal("120.131512"));
        System.out.println(integrationService.getDrawBookId(param));
    }


    @Test
    public void contextLoads() {
        JSONPublicParam<IntegrationDrawParams> params = new JSONPublicParam<>();
        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setUserId(110);
        params.setAuthUserInfo(authUserInfo);
        IntegrationDrawParams param = new IntegrationDrawParams();
        param.setIntegrationDrawType((byte) 1);
        params.setRequestParam(param);
        List<IntegrationDrawDto> yes = Lists.newArrayList();
        List<IntegrationDrawDto> no = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
        }
        System.out.println("中奖数量:" + yes.size());
        System.out.println("未中奖数量:" + no.size());
    }

    @Test
    public void testLotteryAward() throws InterruptedException {
        JSONPublicParam<OpenLotteryAwardParams> params = new JSONPublicParam<>();
        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setUserId(110);
        params.setAuthUserInfo(authUserInfo);
        OpenLotteryAwardParams param = new OpenLotteryAwardParams();
        param.setLotteryId(52);
        params.setRequestParam(param);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                OpenLotteryAwardDto drawDto = lotteryService.doOpenLotteryAward(params);
                System.out.println(JSON.toJSONString(drawDto));
//				if(!drawDto.getCouponCode().equals("NOT_LOTTERY_AWARD") ){
//					System.out.println(">>>>>>>>>>中奖了");
//				}else{
//					System.out.println(">>>>>>>>>>未中奖");
//				}
            }).start();
        }
    }

    @Test
    public void testSocket() {
        for (int i = 0; i < 10; i++) {
            socketSendService.sendSocketMessage("1231231", SystemEnum.SocketType.DRAW.getCode());
        }

    }

}
