package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.constant.ResultJson;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.entity.StudentLesson;
import com.qcby.db.entity.SysUser;
import com.qcby.db.mapper.LessonMapper;
import com.qcby.db.service.LessonService;
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
 * <br>CreateDate 2021/9/16 0:42
 */
@Service("lesson")
@Slf4j
@Transactional
public class LessonServiceImpl extends ServiceImpl<LessonMapper, StudentLesson> implements LessonService {
    @Autowired
    private LessonMapper lessonMapper;


    @Override
    public com.qcby.db.common.web.ResultJson listAll() {
        List<StudentLesson> studentLessonList = lessonMapper.listAll();
        return com.qcby.db.common.web.ResultJson.ok(studentLessonList);
    }

    @Override
    public com.qcby.db.common.web.ResultJson startCourse(String subject, Long classId,
                                                         HttpServletRequest request) {

        String token = request.getHeader("token");
        SysUser sysUser = QcbyContext.getUser(token);
        Long teacherId = sysUser.getId();
        QcbyContext.add(token, teacherId);

        int count = lessonMapper.startCourse(subject, classId, teacherId);
        if (count == ResultJson.WOMAN){
            return com.qcby.db.common.web.ResultJson.ok();
        }else{
            return com.qcby.db.common.web.ResultJson.error();
        }

    }
}
