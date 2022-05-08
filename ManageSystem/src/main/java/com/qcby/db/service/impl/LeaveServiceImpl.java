package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.constant.ResultJson;
import com.qcby.db.entity.StudentLeave;
import com.qcby.db.mapper.LeaveMapper;
import com.qcby.db.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 3:45
 */
@Service("leave")
@Slf4j
@Transactional
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, StudentLeave> implements LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;


    @Override
    public com.qcby.db.common.web.ResultJson listAll(String studentName, String startTime, String endTime) {
        List<StudentLeave> list = leaveMapper.listAll(studentName, startTime, endTime);
        return com.qcby.db.common.web.ResultJson.ok(list);
    }

    @Override
    public com.qcby.db.common.web.ResultJson update(StudentLeave studentLeave) {
        int count = leaveMapper.update(studentLeave);
        if (count == ResultJson.WOMAN){
            return com.qcby.db.common.web.ResultJson.ok();
        }else{
            return com.qcby.db.common.web.ResultJson.error();
        }
    }
}
