package com.qcby.db.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO MybatisPlus配置类
 *
 * @author ZT
 * <br>CreateDate 2021/9/3 3:57
 */
////@Configuration
////@MapperScan("com.baomidou.cloud.service.*.mapper*")
////public class MybatisPlusConfig {
////
////
////    @Bean
////    public MybatisPlusInterceptor mybatisPlusInterceptor() {
////        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
////        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
////        return interceptor;
////
////    }
//
//
//}
