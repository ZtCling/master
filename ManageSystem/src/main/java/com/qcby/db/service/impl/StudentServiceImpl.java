package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.constant.ResultJson;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.entity.SysUser;
import com.qcby.db.mapper.LoginMapper;
import com.qcby.db.mapper.StudentMapper;
import com.qcby.db.service.StudentService;
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
 * <br>CreateDate 2021/9/14 17:02
 */
@Service("student")
@Transactional // 事务
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, SysUser> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<SysUser> listAll(String userName, String realName, String sex,
                                 String admissionDate, String className,
                                 String instituteName, String majorName) {
        return studentMapper.listAll(userName, realName, sex, admissionDate,
                className, instituteName, majorName);
    }

    @Override
    public com.qcby.db.common.web.ResultJson insertUser(SysUser user, HttpServletRequest request) {
        int count = studentMapper.insertUser(user);
        if (count == ResultJson.WOMAN){
            String token = request.getHeader("token");
            SysUser userDb = QcbyContext.getUser(token);
            int code = studentMapper.insertUserWithRole(user.getId());
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
        int count = studentMapper.deleteUser(uId);
        if (count == ResultJson.WOMAN){
            int code = studentMapper.insertUserWithRole(uId);
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
