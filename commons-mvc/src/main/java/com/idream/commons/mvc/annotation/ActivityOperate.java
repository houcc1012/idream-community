package com.idream.commons.mvc.annotation;

import com.idream.commons.lib.enums.ActivityEnum;

import java.lang.annotation.*;

/**
 * 通知记录
 *
 * @author charles
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ActivityOperate {
    ActivityEnum.OperateCategory operateCategory();

    ActivityEnum.OperateType operateType();
}
