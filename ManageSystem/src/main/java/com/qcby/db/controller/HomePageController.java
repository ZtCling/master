package com.qcby.db.controller;

import com.qcby.db.common.web.ResultJson;
import com.qcby.db.service.LoginService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO 菜单
 *
 * @author ZT
 * <br>CreateDate 2021/9/13 16:33
 */
@RestController
@RequestMapping("home")
//日志的两种形式之一
@Slf4j
public class HomePageController {
    @Autowired
    private LoginService loginService;


    @RequestMapping("menuTree")
    public ResultJson menuTree(HttpServletRequest request) {
        return loginService.menuTree(request);
    }
}
