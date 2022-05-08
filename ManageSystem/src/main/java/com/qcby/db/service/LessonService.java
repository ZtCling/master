package com.qcby.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.StudentLesson;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 0:41
 */
public interface LessonService extends IService<StudentLesson> {

    ResultJson<StudentLesson> listAll();

    ResultJson startCourse(String subject, Long classId, HttpServletRequest request);


}
