package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.db.entity.StudentLeave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 3:44
 */
public interface LeaveMapper extends BaseMapper<StudentLeave> {
    List<StudentLeave> listAll(@Param("studentName") String studentName,
                               @Param("startTime") String startTime,
                               @Param("endTime") String endTime);


    int update(@Param("leave") StudentLeave studentLeave);

}
