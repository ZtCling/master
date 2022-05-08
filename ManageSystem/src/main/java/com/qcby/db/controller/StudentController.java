package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysUser;
import com.qcby.db.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/14 17:00
 */
@RestController
@RequestMapping("student")
//日志的两种形式之一
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiOperation("查询学生接口")
    @PreAuth("student:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看学生列表")
    @RequestMapping(value = "listAll")
    public ResultJson listAll(String userName, String realName, String sex,
                              String admissionDate, String className,
                              String instituteName, String majorName) {
        List<SysUser> sysUserList = studentService.listAll(userName, realName, sex, admissionDate,
                className, instituteName, majorName);
        return ResultJson.ok(sysUserList);
    }

    @ApiOperation("添加学生信息接口")
    @PreAuth("student:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加学生信息")
    @RequestMapping(value = "insert")
    public ResultJson insert(SysUser user, HttpServletRequest request){
        return studentService.insertUser(user, request);
    }


    @ApiOperation("修改学生信息接口")
    @PreAuth("student:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "修改学生信息")
    @RequestMapping(value = "update")
    public ResultJson update(SysUser user){
        boolean r = studentService.updateById(user);
        return ResultJson.result(r);
    }

    @ApiOperation("删除学生信息接口")
    @PreAuth("student:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除学生信息")
    @RequestMapping(value = "delete")
    public ResultJson delete(Long uId, HttpServletRequest request){
        return ResultJson.ok(studentService.deleteUser(uId, request));
    }





}
