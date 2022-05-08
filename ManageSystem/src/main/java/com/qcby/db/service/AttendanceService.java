package com.qcby.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.StudentLessonAttendance;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpCookie;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 2:04
 */
public interface AttendanceService extends IService<StudentLessonAttendance> {

    ResultJson<StudentLessonAttendance> listAll(Long userId, HttpServletRequest request);

}
