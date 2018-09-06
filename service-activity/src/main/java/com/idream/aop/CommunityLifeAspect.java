package com.idream.aop;

import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.EgisLifeRecordMapper;
import com.idream.commons.lib.mapper.UserComplaintHandleRecordMapper;
import com.idream.commons.lib.model.EgisLifeRecord;
import com.idream.commons.lib.model.UserComplaintHandleRecord;
import com.idream.commons.lib.util.RequestValueUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class CommunityLifeAspect {
    private static final Logger logger = LoggerFactory.getLogger(CommunityLifeAspect.class);

    @Autowired
    private UserComplaintHandleRecordMapper userComplaintHandleRecordMapper;
    @Autowired
    private EgisLifeRecordMapper egisLifeRecordMapper;

    @Pointcut("execution(* com.idream.service.impl.AppMyNeighborServiceImpl.addMyDynamic(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint pj) throws Throwable {
        Integer userId = RequestValueUtils.getUserId();
        UserComplaintHandleRecord userComplaintHandleRecord = userComplaintHandleRecordMapper.queryUserBanStatus(userId);
        if (userComplaintHandleRecord != null) {
            throw new BusinessException("9999", "禁言中,不能发布动态");
        }
        pj.proceed();
    }

    @AfterReturning(value = "execution(* com.idream.service.impl.AppMyNeighborServiceImpl.getMyNeighborDynamicList(..))")
    public void after(JoinPoint jp) {
        try {
            Integer userId = RequestValueUtils.getUserId();
            Integer lineId = egisLifeRecordMapper.selectMaxLineIdByUserId(userId);
            EgisLifeRecord record = egisLifeRecordMapper.selectByUserId(userId);
            if (record == null) {
                record = new EgisLifeRecord();
                record.setLineId(0);
                Date date = new Date();
                record.setCreateTiem(date);
                record.setUpdateTime(date);
                record.setUserId(userId);
                egisLifeRecordMapper.insertSelective(record);
            }else{
                record.setLineId(lineId);
                egisLifeRecordMapper.updateByPrimaryKey(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户动态维护记录失败");
        }
    }
}
