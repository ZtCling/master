package com.qcby.db.common.web;


import com.qcby.db.util.StringUtil;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @className: InitBinderDateController
 * @description: 全局时间入参格式化
 * @author: lxt
 * @create: 2021-04-25 15:29
 **/
@ControllerAdvice
public class InitBinderDateController {


    /**
     * 将前台传递过来的日期格式的字符串，自动转化为时间类型
     * <p>
     * [拦截不到@RequestBody注解修饰的参数]
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
//
// Date 类型转换
//        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
////        System.out.println("test");
        // LocalDateTime 类型转换
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (!StringUtil.isEmpty(text)) {
                    setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
            }
        });
    }
}
