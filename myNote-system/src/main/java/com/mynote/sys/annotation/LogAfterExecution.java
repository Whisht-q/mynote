package com.mynote.sys.annotation;

import com.mynote.base.constant.Enum.EventTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhishubin
 * @date 2023/12/19 11:54
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAfterExecution {
    String value() default "";  // 可以添加一些额外的信息，例如操作名称
    EventTypeEnum eventType();  // 添加枚举类参数
}
