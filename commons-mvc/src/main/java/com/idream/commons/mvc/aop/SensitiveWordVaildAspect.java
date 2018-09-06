package com.idream.commons.mvc.aop;

import com.idream.commons.lib.annotation.SensitiveWordVaild;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.mvc.utils.SensitiveWordUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * @author hejiang
 * @date 2018/8/27
 */
@Component
@Aspect
public class SensitiveWordVaildAspect {
    private static final Logger logger = LoggerFactory.getLogger(SensitiveWordVaildAspect.class);

    //基本类型 和 基本类型的包装类型
    private static final List<String> TYPES = Arrays.asList(new String[]{
            "int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte",
            "java.lang.Integer", "java.lang.Boolean", "java.lang.Character",
            "java.lang.Float", "java.lang.Double", "java.lang.Long", "java.lang.Short", "java.lang.Byte"});

    @Pointcut("@annotation(com.idream.commons.lib.annotation.SensitiveWordVaild)")
    public void SensitiveWordVaildPointcut() {

    }

    @Before("SensitiveWordVaildPointcut()")
    public void sensitiveWordVaild(JoinPoint point) {
        logger.info("敏感字校验开始");
        // 方法参数
        Object[] args = point.getArgs();

        //处理普通参数
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        for(int j = 0; j < parameters.length; j++){
            Parameter parameter = parameters[j];
            Annotation[] annotations = parameter.getAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof SensitiveWordVaild && args[j] instanceof String){
                    boolean flag = SensitiveWordUtil.contains((String) args[j]);
                    if(flag){
                        throw new BusinessException(RetCodeConstants.SENSITIVE_WORD_VERIFY_NOT_PASS, "内容含有敏感字，请检查后重新提交！");
                    }
                }
            }
        }

        // 校验以java bean对象 为方法参数的
        for (Object obj : args) {
            // 获取对象类型
            String typeName = obj.getClass().getTypeName();
            if(!TYPES.contains(typeName)){
                //得到类中的所有属性集合
                vaildObjField(obj);
            }
        }
    }

    /**
     * 递归校验java bean 字段是否含有敏感字
     * @param obj
     */
    private void vaildObjField(Object obj) {
        Field[] fs = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            if(f.getAnnotation(SensitiveWordVaild.class) != null){
                f.setAccessible(true);
                try {
                    //属性值
                    Object val = f.get(obj);
                    if(val instanceof String ){
                        //字符串类型校验
                        boolean flag = SensitiveWordUtil.contains((String) val);
                        if(flag){
                            throw new BusinessException(RetCodeConstants.SENSITIVE_WORD_VERIFY_NOT_PASS, "内容含有敏感字，请检查后重新提交！");
                        }
                    }else{
                        vaildObjField(val);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
