package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysMenu;
import com.qcby.db.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/14 14:38
 */
@RestController
@RequestMapping("menu")
//日志的两种形式之一
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;


    @PreAuth("menu:listAll")
    @RequestMapping(value = "listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看菜单")
    public ResultJson listAll(){
        List<SysMenu> sysMenuList = menuService.list();
        return ResultJson.ok(sysMenuList);
    }

    @PreAuth("menu:insert")
    @RequestMapping(value = "insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加菜单")
    public ResultJson insert(SysMenu sysMenu){
        boolean r = menuService.save(sysMenu);
        return ResultJson.result(r);
    }

    @PreAuth("menu:delete")
    @RequestMapping(value = "delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除菜单")
    public ResultJson delete(List<Long> idList){
        boolean r = menuService.removeByIds(idList);
        return ResultJson.result(r);
    }

    @PreAuth("menu:update")
    @RequestMapping(value = "update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "修改菜单")
    public ResultJson update(SysMenu sysMenu){
        boolean r = menuService.updateById(sysMenu);
        return ResultJson.result(r);
    }

}
