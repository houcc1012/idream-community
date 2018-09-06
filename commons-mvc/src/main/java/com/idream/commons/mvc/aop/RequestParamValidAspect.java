package com.idream.commons.mvc.aop;//package com.idream.config;

import com.idream.commons.lib.exception.RequestParamValidException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

/**
 * @author hejiang
 */
@Aspect
@Configuration
public class RequestParamValidAspect {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final ExecutableValidator methodValidator = factory.getValidator().forExecutables();

    private final Validator beanValidator = factory.getValidator();

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return methodValidator.validateParameters(obj, method, params);
    }

    private <T> Set<ConstraintViolation<T>> validBeanParams(T bean) {
        return beanValidator.validate(bean);
    }

    @Pointcut("execution (* com.idream.controller..*(..))")
    public void soaServiceBefore() {
    }

    @Before("soaServiceBefore()")
    public void twiceAsOld1(JoinPoint point) {
        Object target = point.getThis();
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        //校验常规参数
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);
        Iterator<ConstraintViolation<Object>> violationIterator = validResult.iterator();
        while (violationIterator.hasNext()) {
            throw new RequestParamValidException(violationIterator.next().getMessage());
        }

        // 校验以java bean对象 为方法参数的
        for (Object bean : args) {
            if (bean != null) {
                validResult = validBeanParams(bean);
                violationIterator = validResult.iterator();
                while (violationIterator.hasNext()) {
                    throw new RequestParamValidException(violationIterator.next().getMessage());
                }
            }
        }
    }
}
