package com.idream;

import com.idream.commons.lib.dto.wangyi.GroupAdviceRequestDto;
import com.idream.commons.lib.dto.wangyi.UpdateIMUserInfoRequestDto;
import com.idream.service.WangYiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThirdInterfaceApplicationTests {


    @Test
    public void update() {
        UpdateIMUserInfoRequestDto updateIMUserInfoRequestDto = new UpdateIMUserInfoRequestDto();
        updateIMUserInfoRequestDto.setAccid("9251f70b9ea04e868c8704c456a13125");
        updateIMUserInfoRequestDto.setIcon("https://img.zfaex.com/group1/M00/00/09/wKglhVsSjayAeVbWAAC1XRbtxmA375.png");
        updateIMUserInfoRequestDto.setName("凤翮筑梦书屋");
        wangYiService.updateIMUserInfo(updateIMUserInfoRequestDto);

    }

    @Test
    public void sendTest() throws Exception {
        int index = 0;
//		while(true){
//			sender.send(DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));
//			System.out.println("123");
//			Thread.sleep(1000);
//			if(index == 10){
//				break;
//			}
//			index ++;
//		}

        Thread.sleep(100000);
    }

    @Autowired
    private WangYiService wangYiService;

    @Test
    public void testSendGroupAdvice() {

        GroupAdviceRequestDto dto = new GroupAdviceRequestDto();
        dto.setTo("489700813");
        //dto.setMsgtype(1);
        dto.setFrom("1ad993e47718498a89ae5deab87794fc");
        //dto.setAttach(" XXXX进入了群组 ");
        wangYiService.groupAdvice(dto);

    }


}
