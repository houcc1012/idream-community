package com.idream.aop;

import com.idream.commons.lib.enums.ActivityEnum;
import com.idream.commons.lib.mapper.ActivityOperateRecordMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.model.ActivityOperateRecord;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.util.RequestValueUtils;
import com.idream.commons.mvc.annotation.ActivityOperate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author charles
 */
@Component
@Aspect
public class ActivityOperateAspect {

    private static final Logger logger = LoggerFactory.getLogger(ActivityOperateAspect.class);
    @Autowired
    private ActivityOperateRecordMapper activityOperateRecordMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Pointcut("@annotation(com.idream.commons.mvc.annotation.ActivityOperate)")
    private void pointCut() {
    }

    @AfterReturning(pointcut = "pointCut()", returning = "rtv")
    public void addActivity(JoinPoint jp, Object rtv) {
        try {
            MethodSignature signature = (MethodSignature) jp.getSignature();
            Method method = signature.getMethod();
            ActivityOperate annotation = method.getAnnotation(ActivityOperate.class);
            ActivityEnum.OperateCategory operateCategory = annotation.operateCategory();
            ActivityEnum.OperateType operateType = annotation.operateType();


            Integer userId = RequestValueUtils.getUserId();
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            String nickName = userInfo.getNickName();

            ActivityOperateRecord record = new ActivityOperateRecord();
            record.setActivityId((Integer) rtv);
            record.setCategory(operateCategory.getCode());
            record.setType(operateType.getCode());
            record.setUserId(userId);
            record.setCreateTime(new Date());
            record.setContent(String.format("用户:%s,%s %s,%n时间:%s,ip:%s", nickName, operateCategory.getContent(), operateType.getContent(), LocalDateTime.now().toString(), RequestValueUtils.getIp()));

            activityOperateRecordMapper.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("记录活动操作报错");
        }
    }
}
