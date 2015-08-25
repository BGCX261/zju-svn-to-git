/**
 * @(#)CodeType.java 2008-10-9 14:36:16 Administrator 版权所有 (c) 2007-2008
 *                   江苏鸿信系统集成有限公司
 */
package com.jsict.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeTypeDef {
    public abstract java.lang.String value() default "";
}