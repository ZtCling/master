package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 2:02
 */
@RestController
@RequestMapping("attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    HttpServletRequest request;

    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看考勤列表")
    @RequestMapping("listAll")
    @PreAuth("attendance:listAll")
    public ResultJson listAll(Long userId){
        return attendanceService.listAll(userId, request);
    }

}
