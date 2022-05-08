package com.qcby.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.StudentLeave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 3:45
 */
public interface LeaveService extends IService<StudentLeave> {
    ResultJson<StudentLeave> listAll(String studentName, String startTime, String endTime);

    ResultJson update(StudentLeave studentLeave);


}
