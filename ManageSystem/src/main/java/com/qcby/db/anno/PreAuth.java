package com.qcby.db.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName PreAuth
 * @Description 权限校验注解
 * @Author lxt
 * @Date 2021/8/18 21:26
 */
@Target(ElementType.METHOD)  // 该注解可以作用于那些类型元素上：类、方法、字段
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
public @interface PreAuth {

    /**
     * 权限字符串
     *
     * @return
     */
    String value() default "";

}
