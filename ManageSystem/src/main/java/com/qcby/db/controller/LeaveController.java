package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.StudentLeave;
import com.qcby.db.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 3:47
 */
@Slf4j
@RestController
@RequestMapping("leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @RequestMapping("listAll")
    @PreAuth("leave:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看请假信息")
    public ResultJson listAll(String studentName, String startTime, String endTime){
        return ResultJson.ok(leaveService.listAll(studentName, startTime, endTime));
    }

    @RequestMapping("update")
    @PreAuth("leave:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "审批请假")
    public ResultJson update(StudentLeave studentLeave){
        return leaveService.update(studentLeave);
    }


    /*
     * @Author liu-miss
     * @Description //TODO 学生增加请假接口
     * @Date l 2021/9/16
     * @Param com.qcby.db.common.web.ResultJson
     * @return [studentLeave]
     **/
    @RequestMapping("studentLeave")
    @PreAuth("leave:studentLeave")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "学生请假")
    public ResultJson studentLeave(StudentLeave studentLeave){
        boolean r =  leaveService.save(studentLeave);
        return ResultJson.result(r);
    }


    /*
     * @Author liu-miss
     * @Description //TODO 学生删除请假接口
     * @Date l 2021/9/16
     * @Param com.qcby.db.common.web.ResultJson
     * @return [studentLeave]
     **/
    @RequestMapping("studentDelete")
    @PreAuth("leave:studentDelete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除请假")
    public ResultJson studentDelete(@RequestParam List<Long> idList){
        boolean r =  leaveService.removeByIds(idList);
        return ResultJson.result(r);
    }


    /*
     * @Author liu-miss
     * @Description //TODO 学生修改请假接口
     * @Date l 2021/9/16
     * @Param com.qcby.db.common.web.ResultJson
     * @return [studentLeave]
     **/
    @RequestMapping("studentUpdate")
    @PreAuth("leave:studentUpdate")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "修改请假")
    public ResultJson studentUpdate(@RequestParam List<Long> idList){
        boolean r =  leaveService.removeByIds(idList);

        return ResultJson.result(r);
    }




}
