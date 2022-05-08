package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.db.entity.StudentLesson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 0:41
 */
public interface LessonMapper extends BaseMapper<StudentLesson> {

    List<StudentLesson> listAll();

    int startCourse(@Param("subject") String subject,
                    @Param("classId") Long classId,
                    @Param("teacherId") Long teacherId);
}
