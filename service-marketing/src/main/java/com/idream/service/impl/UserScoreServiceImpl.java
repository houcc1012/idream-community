package com.idream.service.impl;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.IntegrationInfoMapper;
import com.idream.commons.lib.mapper.IntegrationRecordMapper;
import com.idream.commons.lib.model.IntegrationInfo;
import com.idream.commons.lib.model.IntegrationRecord;
import com.idream.service.UserScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author hejiang
 */
@Service
public class UserScoreServiceImpl implements UserScoreService {

    @Resource
    private IntegrationInfoMapper integrationInfoMapper;

    @Resource
    private IntegrationRecordMapper integrationRecordMapper;

    @Override
    public int addUserScoreRecord(Integer score, Byte fromType, Byte recordType, Integer userId) {
        return addUserScoreRecord(score, fromType, recordType, null, userId);
    }

    @Override
    public int updateUserScoreAndFreeLottery(Integer score, Integer freeLottery, Integer userId) {
        return integrationInfoMapper.updateScoreAndFreeLotteryByUserId(score, freeLottery, userId);
    }

    @Override
    public void updateUserScoreAndRecord(Integer score, Byte fromType, Byte recordType, Integer authUserId) {
        updateUserScoreAndRecord(score, fromType, recordType, 0, authUserId);
    }

    @Override
    public void updateUserScoreAndRecord(Integer score, Byte fromType, Byte recordType, Integer freeLottery, Integer authUserId) {
        updateUserScoreAndRecord(score, fromType, recordType, freeLottery, 0, authUserId);
    }

    @Override
    public void updateUserScoreAndRecord(Integer score, Byte fromType, Byte recordType, Integer freeLottery, Integer businessId, Integer authUserId) {
        if (score > 0) {
            if (UserEnum.IntegrationRecordType.SUBTRACT.getCode().equals(recordType)) {
                score = 0 - score;
            }
            //查询用户积分信息
            IntegrationInfo integrationInfo = integrationInfoMapper.selectByUserId(authUserId);
            if (integrationInfo == null) {
                integrationInfo = new IntegrationInfo();
                Date date = new Date();
                integrationInfo.setUpdateTime(date);
                integrationInfo.setCreateTime(date);
                integrationInfo.setUserId(authUserId);
                integrationInfo.setFreeLottery(freeLottery);
                integrationInfo.setScore(score);
                integrationInfoMapper.insertSelective(integrationInfo);
            } else {
                int updateResult = integrationInfoMapper.updateScoreAndFreeLotteryByUserId(score, 0, authUserId);
                if (updateResult < 1) {
                    throw new BusinessException(RetCodeConstants.SIGN_ERROR, "添加用户积分失败！");
                }
            }
            //记录用户积分使用记录
            addUserScoreRecord(Math.abs(score), fromType, recordType, businessId, authUserId);
        }
    }

    @Override
    public int addUserScoreRecord(Integer score, Byte fromType, Byte recordType, Integer businessId, Integer userId) {
        IntegrationRecord integrationRecord = new IntegrationRecord();
        Date date = new Date();
        integrationRecord.setUserId(userId);
        integrationRecord.setUpdateTime(date);
        integrationRecord.setCreateTime(date);
        integrationRecord.setType(recordType);
        integrationRecord.setScore(score);
        integrationRecord.setFromType(fromType);
        integrationRecord.setBusinessId(businessId);
        integrationRecord.setFromDescription("");
        return integrationRecordMapper.insertSelective(integrationRecord);
    }
}
