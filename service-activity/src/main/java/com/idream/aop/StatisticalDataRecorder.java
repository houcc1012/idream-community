package com.idream.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idream.commons.lib.base.StatisticalType;
import com.idream.commons.lib.mapper.UserVisitRecordMapper;
import com.idream.commons.lib.model.UserVisitRecord;
import com.idream.commons.mvc.annotation.StatisticalData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 统计数据拦截
 */
@Aspect
@Component
public class StatisticalDataRecorder implements Ordered {

    Logger logger = LoggerFactory.getLogger(StatisticalDataRecorder.class);

    @Resource
    private UserVisitRecordMapper userVisitRecordMapper;

    @Pointcut("@annotation(com.idream.commons.mvc.annotation.StatisticalData)")
    public void methodPointcut() {

    }

    /**
     * 业务方法执行成功后写人
     *
     * @param jp
     */
    @AfterReturning(value = "methodPointcut()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) throws Throwable {
        try {
            //拦截的方法名称
            Method method = ((MethodSignature) jp.getSignature()).getMethod();
            // 拦截的方法参数
            Object[] args = jp.getArgs();
            // 获取自定义注解实体
            StatisticalData logAnnotation = method.getAnnotation(StatisticalData.class);
            Byte type = logAnnotation.type();
            if (StatisticalType.ACTIVITY == type) {
                //活动浏览量统计
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(result));
                if (jsonObject.get("responseData") == null) {
                    return;
                }
                // 业务ID
                int businessId = (int) args[1];
                // 用户ID
                int userId = (int) args[0];
                //修改信息
                int count = userVisitRecordMapper.updateCountByBusinessIdAndTypeAndUserId(businessId, type, userId);
                // 无修改新增数据
                if (count == 0) {
                    UserVisitRecord record = new UserVisitRecord();
                    record.setBusinessId(businessId);
                    record.setCreateTime(new Date());
                    record.setUserId(userId);
                    record.setBusinessType(type);
                    record.setCount(1);
                    userVisitRecordMapper.insertSelective(record);
                }
            }
        } catch (Exception e) {
            logger.error("记录用户统计数据失败", e);
        }
        return;
    }

    /**
     * 执行的业务方法出现异常
     *
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "methodPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        return;
    }


    @Override
    public int getOrder() {
        return 1;
    }

}
