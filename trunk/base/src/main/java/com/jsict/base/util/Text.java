package com.jsict.base.util;

@java.lang.annotation.Target(value = { java.lang.annotation.ElementType.METHOD,
        java.lang.annotation.ElementType.FIELD })
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public abstract @interface Text {

    public abstract java.lang.String label() default "";

    public abstract java.lang.String format() default "";

    public abstract boolean convert() default true;
    
}