package com.qcby.db.anno;

import com.qcby.db.common.contest.GlobalConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO 自定义日志注解
 * @author ZT
 * <br>CreateDate 2021/9/17 10:26
 */

@Target(ElementType.METHOD)  // 该注解可以作用于那些类型元素上：类、方法、字段
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
public @interface SelfLog {
    /*
     * @Author liu-miss
     * @Description //TODO 操作类型
     **/
    int type() default GlobalConstant.LOG_TYPE_SELECT;


    /*
     * @Author liu-miss
     * @Description //TODO 模块
     **/
    String module() default "";


    /*
     * @Author liu-miss
     * @Description //TODO 名称参数
     **/
    String name() default "name";

}
