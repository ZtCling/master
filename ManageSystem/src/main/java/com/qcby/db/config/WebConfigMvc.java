package com.qcby.db.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.qcby.db.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

/**
 * @className: WebConfigMvc
 * @description: mvc配置类
 * @author: lxt
 * @create: 2021-08-17 21:07
 **/
@Configuration
public class WebConfigMvc implements WebMvcConfigurer {

    /**
     * @param registry
     * @Description: 添加自定义拦截到spring mvc拦截链中 => 注册自定义拦截器
     * @return: void
     * @Author: lxt
     * @Date: 2021/8/17 21:08
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器
        registry.addInterceptor(loginInterceptor())
                // 定拦截那些路径，/**代表所有
                .addPathPatterns("/**")
                // 那些路径不拦截，排除规则
                .excludePathPatterns("/login", "/error/**", "/swagger-ui.html/**",
                        "/swagger-resources/**", "/webjars/**", "/csrf/**",
                        "/login/logout", "/test/demo");
    }

    //  @Bean 作用等价于 =>  @Component  => @Service => @Controller
    @Bean
    public LoginInterceptor loginInterceptor() {

        return new LoginInterceptor();
    }


    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * @return
     * @Bean <bean id="xxx"></bean>
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }


    //允许跨域
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //允许跨域访问资源定位：所有资源
        registry.addMapping("/**")
                //只允许本地的指定端口号跨域访问
                .allowedOrigins("http://localhost:8081")
                //允许发送凭证，前端如果配置改属性为true之后，则必须同步配置
                .allowCredentials(true)
                //允许所有方法
                .allowedMethods("*");
    }






}




