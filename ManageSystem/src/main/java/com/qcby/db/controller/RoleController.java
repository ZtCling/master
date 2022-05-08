package com.qcby.db.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.common.web.PageWeb;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysRole;
import com.qcby.db.entity.SysUser;
import com.qcby.db.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
 * <br>CreateDate 2021/9/9 10:05
 */
@RestController
@RequestMapping("role")
@Api(tags = {"角色模块--tags"})
//日志的两种形式之一
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("查看所有角色")
    @PreAuth("role:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看角色")
    @RequestMapping(value = "listAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public ResultJson listAll (String roleName, String description, Integer pageNo, Integer pageSize){
        Page<SysRole> page = new Page<>(pageNo, pageSize);
        IPage<SysRole> pageInfo = roleService.listAll(roleName, description, page);
        return ResultJson.ok("查询成功", PageWeb.build(pageInfo));
    }

    @ApiOperation("添加角色")
    @PreAuth("role:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加角色")
    @RequestMapping(value = "insert", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public ResultJson insert(SysRole sysRole){
        boolean r = roleService.save(sysRole);
        return ResultJson.result(r);
    }

    @ApiOperation("修改角色")
    @PreAuth("role:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "修改角色")
    @RequestMapping(value = "update", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public ResultJson update(SysRole sysRole){
        boolean r = roleService.updateById(sysRole);
        return ResultJson.result(r);
    }


    @ApiOperation("删除角色")
    @PreAuth("role:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除角色")
    @RequestMapping(value = "delete", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public ResultJson delete(@RequestParam List<Long> idList){
        boolean r = roleService.removeByIds(idList);
        return ResultJson.result(r);
    }


    @ApiOperation("查看自己和全部的角色")
    @RequestMapping(value = "myRole")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看全部角色")
    public ResultJson myRole(HttpServletRequest request){
        String token = request.getHeader("token");
        SysUser userDb = QcbyContext.getUser(token);
        long userId = userDb.getId();
        List<SysRole> sysRoleList = roleService.myRole(userId);
        return ResultJson.ok(sysRoleList);
    }


    @ApiOperation("设置角色权限")
    @RequestMapping(value = "setMenu")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "设置角色")
    public ResultJson setRoleMenu(Long roleId, @RequestParam List<Long> menuIdList) {
        return roleService.setRoleMenu(roleId, menuIdList);
    }






}
