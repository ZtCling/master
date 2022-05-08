package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.StudentLessonAttendance;
import com.qcby.db.entity.SysUser;
import com.qcby.db.mapper.AttendanceMapper;
import com.qcby.db.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 2:04
 */
@Service("attendance")
@Slf4j
@Transactional
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, StudentLessonAttendance>
        implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;


    @Override
    public ResultJson<StudentLessonAttendance> listAll(Long userId, HttpServletRequest request) {
//        String token = request.getHeader("token");
//        SysUser userDb = QcbyContext.getUser(token);
//        long uId = userDb.getId();
//        QcbyContext.add(token, uId);
        List<StudentLessonAttendance> list = attendanceMapper.listAll(userId);
        return ResultJson.ok(list);
    }
}
