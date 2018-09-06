package com.idream;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.DiscoveryPageRequestParams;
import com.idream.commons.lib.dto.activity.MiniActivityDiscoverypageResponseDto;
import com.idream.commons.lib.dto.appactivity.AppActivityListDto;
import com.idream.commons.lib.dto.appactivity.AppActivityListRequestDto;
import com.idream.commons.mvc.utils.SensitiveWordUtil;
import com.idream.service.ActivityDisplayService;
import com.idream.service.AppActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/19 17:02
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityTest {

    @Resource
    ActivityDisplayService activityDisplayService;

    @Autowired
    private AppActivityService appActivityService;

    @Test
    public void sensitiveWord(){
        String str = "周末的日子怎么打发才好?凡夫俗子只关心如何打发时间，而略具才华的人却考虑如何利用时间。头脑思想狭隘的人容易受到无聊的侵袭，其原因就是他们的智力纯碎服务于他们的意欲，是意欲的工具。简单说来就是，智慧的人和懒惰的人对待周末时间的方式区别。" +
                "别的结果方式是有若为微小的收获而沾沾自喜，本身就是一种无价值的表现。傻逼最渺小的人常关注成绩和荣耀，最伟大的人常沉浸于创造和劳动。作家们总是从生活里面最不起眼的角落里发现最真实的哲理和心灵鸡汤。";

        long time = System.currentTimeMillis();
        for(int i = 0 ; i < 10 ; i++){
            System.out.println(SensitiveWordUtil.contains(str));
        }
        System.out.println(System.currentTimeMillis() - time);
    }


    @Test
    public void getNearbyActivityByTypeIdOrLocation() throws InterruptedException {
        //activityDisplayService.removeUserJoinActivity("231,232", 891);
        AppActivityListRequestDto params = new AppActivityListRequestDto();
        params.setCityCode("330100");
        params.setLatitude(new BigDecimal(120.131485));
        params.setLongitude(new BigDecimal(30.265423));
        params.setTypeId(9);
        params.setPage(1);
        params.setRows(11);
        PagesDto<AppActivityListDto> pagesDto = appActivityService.getNearbyActivityByTypeIdOrLocation(127, params);
        System.err.println(JSON.toJSON(pagesDto));
    }



    @Test
    public void joinGroup() throws InterruptedException {
        //activityDisplayService.removeUserJoinActivity("231,232", 891);
        Thread.sleep(100000L);
    }

    @Test
    public void testShowMinActivity() throws InterruptedException {
        DiscoveryPageRequestParams params = new DiscoveryPageRequestParams();
        params.setCityCode("330100");
        params.setLatitude("30.265438079833984");
        params.setLongitude("120.1318359375");
        params.setTypeId("0");
        params.setPage(1);
        params.setRows(20);


        PagesDto<MiniActivityDiscoverypageResponseDto> data = activityDisplayService.miniDiscoverypage(params);
    }


}

