package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.IntegrationConfigCodeConstans;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.integration.FindUserIntergrationInfoDto;
import com.idream.commons.lib.dto.integration.FindUserSignRankingListDto;
import com.idream.commons.lib.dto.integration.OldIntegrationConfigDto;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.enums.MarketingEnum;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.IntegrationConfigMapper;
import com.idream.commons.lib.mapper.IntegrationInfoMapper;
import com.idream.commons.lib.mapper.IntegrationRecordMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.model.IntegrationInfo;
import com.idream.commons.lib.model.IntegrationRecord;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.mvc.annotation.Achievement;
import com.idream.feign.FeignMarketingService;
import com.idream.service.UserIntegralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright: Copyright (c) 2018 Company: www.idream.com
 *
 * @author huayuliang
 * @version 1.0
 * @date 2018年4月11日
 */
@Service
public class UserIntegralServiceImpl implements UserIntegralService {

    private static final Logger logger = LoggerFactory.getLogger(UserIntegralServiceImpl.class);

    //签到积分最大累计天数
    private static final int SIGN_INTERRATION_DAY = 7;

    @Autowired
    private IntegrationConfigMapper integrationConfigMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private IntegrationRecordMapper integrationRecordMapper;

    @Resource
    private IntegrationInfoMapper integrationInfoMapper;

    @Resource
    private FeignMarketingService feignMarketingService;

    @Override
    public int updateSignCofig(JSONPublicParam<OldIntegrationConfigDto> param) {
        OldIntegrationConfigDto dto = param.getRequestParam();
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.ONE_DAY, dto.getOne());
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.TWO_DAY, dto.getTwo());
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.THREE_DAY, dto.getThree());
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.FOUR_DAY, dto.getFour());
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.FIVE_DAY, dto.getFive());
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.SIX_DAY, dto.getSix());
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.SEVEN_DAY, dto.getSeven());
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.ACTIVITY_TASK_SCORE, dto.getTaskScore());
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.LOTTERY_ENABLE, dto.getLotteryEnable());
        return 0;
    }

    @Override
    public OldIntegrationConfigDto getSignCofig() {
        OldIntegrationConfigDto dto = new OldIntegrationConfigDto();
        dto.setOne(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.ONE_DAY));
        dto.setTwo(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.TWO_DAY));
        dto.setThree(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.THREE_DAY));
        dto.setFour(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.FOUR_DAY));
        dto.setFive(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.FIVE_DAY));
        dto.setSix(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.SIX_DAY));
        dto.setSeven(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.SEVEN_DAY));
        dto.setLotteryEnable(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.LOTTERY_ENABLE));
        dto.setTaskScore(integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.ACTIVITY_TASK_SCORE));
        return dto;
    }

    /**
     * @Author: hejiang
     * @Description: 签到获取积分
     * @Date: 15:09 2018/4/12
     */
    @Override
    @Achievement(eventType = EventEnum.EventType.SIGN)
    public void doSignGetIntegration(int authUserId) {
        //校验用户是否存在
        UserInfo user = userInfoMapper.selectByPrimaryKey(authUserId);
        if (user == null) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "用户不存在!");
        }
        //校验用户是否当天第一次签到
        IntegrationRecord record =
                integrationRecordMapper.selectByUserIdAndFromTypeAndTime(authUserId, UserEnum.IntegrationFromType.SIGN.getCode());
        if (record != null) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "您今天已经签到过了!");
        }
        //查询用户积分配置
        Integer oneScore = integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.ONE_DAY);
        if (oneScore == null) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "签到积分配置未配置，请联系管理员配置!");
        }
        //查询用户是否断签
        Integer recordId = integrationRecordMapper.selectSignOffByUserId(authUserId);
        Integer signDay = 0;
        if (recordId != null && recordId > 0) {
            //没有断签的情况查询用户当前是第几天签到,可以获得的积分是多少
            signDay = integrationRecordMapper.selectSignDayByUserId(authUserId);
            signDay = signDay == null ? 0 : signDay;
        }
        //获取签到积分
        int score = getInteration(signDay + 1);
        //用户是否可以获得免费抽奖机会
        int freeLottery = 0;
        if (integrationConfigMapper.getBooleanByCode(IntegrationConfigCodeConstans.LOTTERY_ENABLE)) {
            freeLottery = (signDay + 1) % SIGN_INTERRATION_DAY == 0 ? 1 : 0;
        }
        //给用户新增签到积分
        feignMarketingService.addUserScore(score, MarketingEnum.ScoreFromType.SIGN.getCode(),
                UserEnum.IntegrationRecordType.ADD.getCode(), freeLottery, authUserId);
    }

    @Override
    public JSONPublicDto<FindUserIntergrationInfoDto> getUserIntegrationInfo(int authUserId) {
        FindUserIntergrationInfoDto dto = new FindUserIntergrationInfoDto();
        //查询用户是否断签
        Integer recordId = integrationRecordMapper.selectSignOffByUserId(authUserId);
        Integer signDay;
        if (recordId != null && recordId > 0) {
            //查询用户持续签到天数
            signDay = integrationRecordMapper.selectSignDayByUserId(authUserId);
        } else {
            signDay = integrationRecordMapper.selectByUserIdAndToday(authUserId);
        }
        signDay = signDay == null ? 0 : signDay;
        //查询用户积分统计
        IntegrationInfo integrationInfo = integrationInfoMapper.selectByUserId(authUserId);
        if (integrationInfo == null) {
            dto.setIntergrationCount(0);
        } else {
            dto.setIntergrationCount(integrationInfo.getScore());
        }
        dto.setContinuedSignDay(signDay);
        //查询用户今天是否签到
        int count = integrationRecordMapper.selectByUserIdAndToday(authUserId);
        if (count > 0) {
            dto.setTodayIsSign(SystemEnum.TrueFalseCode.TRUE.getCode());
        } else {
            dto.setTodayIsSign(SystemEnum.TrueFalseCode.FALSE.getCode());
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", dto);
    }

    @Override
    public PagesDto<FindUserSignRankingListDto> getUserSignRankingList(int page, int rows) {
        //开启分页
        PageHelper.startPage(page, rows);
        //查询数据
        List<FindUserSignRankingListDto> findUserSignRankingListDtos = integrationRecordMapper.selectUserSignRankingList();
        PageInfo<FindUserSignRankingListDto> pageInfo = new PageInfo<>(findUserSignRankingListDtos);
        long total = pageInfo.getTotal();
        if (pageInfo.getTotal() > 100) {
            total = 100;
        }
        return new PagesDto<FindUserSignRankingListDto>(findUserSignRankingListDtos, total, page, rows);
    }

    /**
     * @Author: hejiang
     * @Description: 获取当前签到天数的签到积分
     * @Date: 19:39 2018/4/12
     */
    private int getInteration(int signDay) {
        String code;
        switch (signDay) {
            case 1:
                code = IntegrationConfigCodeConstans.ONE_DAY;
                break;
            case 2:
                code = IntegrationConfigCodeConstans.TWO_DAY;
                break;
            case 3:
                code = IntegrationConfigCodeConstans.THREE_DAY;
                break;
            case 4:
                code = IntegrationConfigCodeConstans.FOUR_DAY;
                break;
            case 5:
                code = IntegrationConfigCodeConstans.FIVE_DAY;
                break;
            case 6:
                code = IntegrationConfigCodeConstans.SIX_DAY;
                break;
            default:
                code = IntegrationConfigCodeConstans.SEVEN_DAY;
                break;
        }
        return integrationConfigMapper.getIntegerByCode(code);
    }

}
