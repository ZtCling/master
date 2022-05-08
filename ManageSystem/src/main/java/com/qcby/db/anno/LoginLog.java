package com.qcby.db.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 *
 * @author ZT 登录日志注解
 * <br>CreateDate 2021/9/17 20:15
 */
@Target(ElementType.METHOD)  // 该注解可以作用于那些类型元素上：类、方法、字段
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
public @interface LoginLog {

    /*
     * @Author liu-miss
     * @Description //TODO 模块
     **/
    String module() default "";
}
