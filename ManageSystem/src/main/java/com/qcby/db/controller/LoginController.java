package com.qcby.db.controller;

import com.qcby.db.anno.LoginLog;
import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.constant.ResultJson;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.entity.SysUser;
import com.qcby.db.properties.SwaggerProperties;
import com.qcby.db.service.LoginService;
import com.qcby.db.util.StringUtil;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 16:53
 */
@RestController
@RequestMapping("login")
@Api(tags = {"登录模块--tags"})
//日志的两种形式之一
@Slf4j
public class LoginController {

    //日志的两种形式之一
    //protected Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpServletRequest request;

    private SwaggerProperties swaggerProperties;



    @ApiOperation("登录接口")
    @RequestMapping(method ={RequestMethod.POST,RequestMethod.GET} )
    @LoginLog
    public com.qcby.db.common.web.ResultJson login(SysUser user){
        return loginService.login(user, request);
    }





    @ApiOperation("更改密码接口")
    @PreAuth("updatePassword")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "更改密码")
    @RequestMapping(value = "updatePassword", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public com.qcby.db.common.web.ResultJson updatePassword(String passwordOld, String password, String passwordAgain){
        if (StringUtil.isEmpty(passwordOld) || StringUtil.isEmpty(password) || StringUtil.isEmpty(passwordAgain)){
            return com.qcby.db.common.web.ResultJson.error("修改失败1", null);
        }else{
            String token = request.getHeader("token");
            SysUser user = QcbyContext.getUser(token);
            String userPassword = user.getPassword();
            log.info(userPassword);
            log.info(passwordOld);
            log.info(password);
            log.info(passwordAgain);
            if (passwordOld.equals(userPassword.trim()) ){
                if (password.equals(passwordAgain.trim())){
                    loginService.updatePassword(passwordOld, password, passwordAgain);
                    return com.qcby.db.common.web.ResultJson.ok("修改成功");
                }else{
                    return com.qcby.db.common.web.ResultJson.error("修改错误2");
                }
            }else{
                return com.qcby.db.common.web.ResultJson.error("修改错误3", null);
            }
        }
    }

    @ApiOperation("更改个人信息接口")
    @PreAuth("updatePersonalMessage")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "更改个人信息")
    @RequestMapping(value = "updateMessage", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public com.qcby.db.common.web.ResultJson updatePersonMessage(String sex, String email, String realName,
                                                                 String photo){
        String token = request.getHeader("token");
        SysUser user = QcbyContext.getUser(token);
        String userId = String.valueOf(user.getId());
        log.info(userId);
        int count = loginService.updatePersonMessage(sex, email, realName, photo, userId);

        if (count == ResultJson.MAN){
            return com.qcby.db.common.web.ResultJson.error("修改失败", null);
        }else{
            return com.qcby.db.common.web.ResultJson.ok("修改成功", null);
        }
    }

    @ApiOperation("忘记密码接口")
    @PreAuth("forgetPassword")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "忘记密码")
    @RequestMapping(value = "forgetPassword", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public com.qcby.db.common.web.ResultJson forgetPassword(String verCode,
                                                            String password, Long userId){
        return loginService.forgetPassword(request, verCode, password, userId);
    }


    @ApiOperation("登出接口")
    @GetMapping("logout")
//    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "登出接口")
    public com.qcby.db.common.web.ResultJson logout(){
        String token = request.getHeader(ResultJson.TOKEN);
        QcbyContext.remove(token);
        return com.qcby.db.common.web.ResultJson.ok();
    }







}
