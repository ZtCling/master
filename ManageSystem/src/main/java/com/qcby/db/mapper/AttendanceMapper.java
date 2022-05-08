package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.db.anno.PreAuth;
import com.qcby.db.entity.StudentLessonAttendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 2:03
 */
public interface AttendanceMapper extends BaseMapper<StudentLessonAttendance> {
    List<StudentLessonAttendance> listAll(@Param("userId") Long userId);

}
