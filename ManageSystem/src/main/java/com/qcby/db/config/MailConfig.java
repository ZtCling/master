package com.qcby.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/13 1:18
 */
public class MailConfig {
    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setProtocol("SMTP");
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(465);

        return javaMailSender;
    }
}
