/**
 * @(#)CodeKey.java 2008-10-9 14:35:03 Administrator 版权所有 (c) 2007-2008
 *                  江苏鸿信系统集成有限公司
 */
package com.jsict.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * </p>
 * 
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2008-9-24 9:22:38
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeKeyDef {
    String value() default ""; //code key

    int status() default 1;//0-unused 1-used
}
