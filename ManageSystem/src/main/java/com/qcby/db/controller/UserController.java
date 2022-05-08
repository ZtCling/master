package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysUser;
import com.qcby.db.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 14:30
 */

@RestController
@RequestMapping("user")
@Api(tags = {"用户管理模块--tags"})
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("根据姓名查询用户接口")
    @PreAuth("user:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查询用户信息")
    @RequestMapping(value = "listAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public ResultJson<SysUser> listAll(String realName){
        if (realName == null){
            return ResultJson.error(null);
        }else{
            List<SysUser> list = userService.listAll(realName);
            return ResultJson.ok(list);
        }
    }


    @ApiOperation("添加用户接口")
    @PreAuth("user:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加用户信息")
    @RequestMapping(value = "insert", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public ResultJson insert(SysUser user){
        return userService.insert(user);
    }


    @ApiOperation("修改用户接口")
    @PreAuth("user:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "修改用户信息")
    @RequestMapping(value = "update", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public ResultJson update(SysUser sysUser, HttpServletRequest request){
        String token = request.getHeader("token");
        SysUser userDb = QcbyContext.getUser(token);

        boolean r = userService.updateById(sysUser);
        return ResultJson.result(r);
    }



    @ApiOperation("删除用户接口")
    @PreAuth("user:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除用户信息")
    @RequestMapping(value = "delete")
    public ResultJson delete(@RequestParam List<Long> ids){
        boolean r = userService.removeByIds(ids);
        return ResultJson.result(r);
    }



}
