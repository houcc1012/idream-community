package com.idream.commons.mvc.annotation;

import com.idream.commons.lib.enums.EventEnum;

import javax.validation.constraints.Null;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hejiang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Achievement {
    Class classType() default Null.class;

    EventEnum.EventType eventType();
}
