package com.idream;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.dto.app.NeighborChatParams;
import com.idream.commons.lib.dto.user.DecodeWeiChatDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.CreateGroupResponseDto;
import com.idream.commons.lib.dto.wangyi.JoinGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.WangYiUserInfo;
import com.idream.feign.FeignMarketingService;
import com.idream.service.AppChattingService;
import com.idream.service.UserIMGroupService;
import com.idream.service.UserIMService;
import com.idream.service.UserService;
import com.idream.utils.WeichatUserInfoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/19 17:02
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JoinGroupTest {

    @Autowired
    private UserIMGroupService userIMGroupService;

    @Autowired
    private UserIMService userIMService;

    @Autowired
    private WeichatUserInfoUtils weichatUserInfoUtils;

    @Autowired
    private FeignMarketingService feignMarketingService;

    @Autowired
    private AppChattingService appChattingService;

    @Autowired
    private UserService userService;

    @Test
    public void test2(){
        System.out.println(JSON.toJSONString(userService.getUserLocation(1234568, "192.168.37.132")));
    }


    @Test
    public void test1() {
        NeighborChatParams params = new NeighborChatParams();
        params.setCityCode("330100");
        appChattingService.getNeighborChat(26, params);
    }

    @Test
    public void addUserScore() {
        feignMarketingService.addUserScore(123, (byte) 1, 1, 222);
    }

    @Test
    public void createGroup1() {
        CreateGroupRequestParam param = new CreateGroupRequestParam();
        //param.setUserId(142);
        param.setActivityId(352);
        param.setTitle("哈哈");
        CreateGroupResponseDto imGroup = userIMGroupService.createIMGroup(param);
    }


    //用户添加群组
    @Test
    public void joinGroup() {
        JoinGroupRequestParam requestParam = new JoinGroupRequestParam();
        requestParam.setActivityId(186);
        // requestParam.setAuthUserId(109);
        userIMGroupService.doJoinGroup(requestParam);
    }


    //获取用户
    @Test
    public void getUser() {
        WangYiUserInfo wangYiUserInfo = userIMService.doGetIMUser(181);
        System.out.println(wangYiUserInfo);
    }


    @Test
    public void getUser1() {
        DecodeWeiChatDto dto = weichatUserInfoUtils.getUserinfo("071gLDeL0Vtb5620N1cL0xYMeL0gLDeb");
        System.out.println(JSON.toJSONString(dto));
    }

    @Test
    public void createGroup() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            Thread.sleep(500);
            CreateGroupRequestParam param = new CreateGroupRequestParam();
            //param.setUserId(142);
            param.setActivityId(i);
            CreateGroupResponseDto imGroup = userIMGroupService.createGroupDemo(param);
            System.err.println(imGroup.getTid() + ".................................................");
        }

    }

}

