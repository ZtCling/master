package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.StudentLesson;
import com.qcby.db.service.LessonService;
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
 * <br>CreateDate 2021/9/16 0:44
 */
@RestController
@RequestMapping("lesson")
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    HttpServletRequest request;



    @RequestMapping("listAll")
    @PreAuth("lesson:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "查看上课信息")
    public ResultJson listAll(){
        return lessonService.listAll();
    }

    @RequestMapping("insert")
    @PreAuth("lesson:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加上课信息")
    public ResultJson insert(StudentLesson studentLesson){
        boolean r = lessonService.save(studentLesson);
        return ResultJson.result(r);
    }

    @RequestMapping("delete")
    @PreAuth("lesson:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除上课信息")
    public ResultJson delete(@RequestParam List<Long> idList){
        boolean r = lessonService.removeByIds(idList);
        return ResultJson.result(r);
    }

    @RequestMapping("update")
    @PreAuth("lesson:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "修改上课信息")
    public ResultJson update(StudentLesson studentLesson){
        boolean r = lessonService.updateById(studentLesson);
        return ResultJson.result(r);
    }


    @RequestMapping("startCourse")
    @PreAuth("lesson:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "创建课程")
    public ResultJson startCourse(String subject, Long classId){
        return lessonService.startCourse(subject, classId, request);
    }






}
