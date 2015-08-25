package com.jsict.base.util;

@java.lang.annotation.Target(value = { java.lang.annotation.ElementType.METHOD,
        java.lang.annotation.ElementType.FIELD })
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public abstract @interface GeneratedNo {

    public abstract java.lang.String pre() default "";

    public abstract java.lang.String format() default "";

    public abstract java.lang.String end() default "";
    
}