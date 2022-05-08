package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysUser;
import com.qcby.db.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 14:25
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询教师接口")
    @PreAuth("student:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查询教师列表")
    @RequestMapping(value = "listAll")
    public ResultJson listAll() {
        List<SysUser> sysUserList = teacherService.listAll();
        return ResultJson.ok(sysUserList);
    }

    @ApiOperation("添加教师信息接口")
    @PreAuth("student:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加老师信息")
    @RequestMapping(value = "insert")
    public ResultJson insert(SysUser user, HttpServletRequest request){
        return teacherService.insertUser(user, request);
    }


    @ApiOperation("修改教师信息接口")
    @PreAuth("student:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "修改老师信息")
    @RequestMapping(value = "update")
    public ResultJson update(SysUser user){
        boolean r = teacherService.updateById(user);
        return ResultJson.result(r);
    }

    @ApiOperation("删除教师信息接口")
    @PreAuth("student:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除老师信息")
    @RequestMapping(value = "delete")
    public ResultJson delete(Long uId, HttpServletRequest request){
        return ResultJson.ok(teacherService.deleteUser(uId, request));
    }


}
