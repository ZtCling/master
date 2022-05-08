package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.constant.ResultJson;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.entity.SysUser;
import com.qcby.db.mapper.LoginMapper;
import com.qcby.db.mapper.TeacherMapper;
import com.qcby.db.service.TeacherService;
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
 * <br>CreateDate 2021/9/15 14:23
 */
@Service("teacher")
@Slf4j
@Transactional // 事务
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, SysUser> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<SysUser> listAll() {
        return teacherMapper.listAll();
    }

    @Override
    public com.qcby.db.common.web.ResultJson insertUser(SysUser user, HttpServletRequest request) {
        int count = teacherMapper.insertUser(user);
        if (count == ResultJson.WOMAN){
            String token = request.getHeader("token");
            SysUser userDb = QcbyContext.getUser(token);
            int code = teacherMapper.insertUserWithRole(user.getId());
            if (code == ResultJson.WOMAN){
                return com.qcby.db.common.web.ResultJson.ok(null);
            }else{
                return com.qcby.db.common.web.ResultJson.error(null);
            }
        }else{
            return com.qcby.db.common.web.ResultJson.error(null);
        }
    }

    @Override
    public com.qcby.db.common.web.ResultJson deleteUser(Long userId, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser userDb = QcbyContext.getUser(token);
        long uId = userDb.getId();
        int count = teacherMapper.deleteUser(uId);
        if (count == ResultJson.WOMAN){
            int code = teacherMapper.insertUserWithRole(uId);
            if (code == ResultJson.WOMAN){
                return com.qcby.db.common.web.ResultJson.ok(null);
            }else {
                return com.qcby.db.common.web.ResultJson.error(null);
            }
        }else {
            return com.qcby.db.common.web.ResultJson.error(null);
        }
    }
}
