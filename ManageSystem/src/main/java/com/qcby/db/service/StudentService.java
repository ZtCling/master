package com.qcby.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/14 17:02
 */
public interface StudentService extends IService<SysUser> {
    List<SysUser> listAll(String userName, String realName, String sex,
                          String admissionDate, String className,
                          String instituteName, String majorName);

    ResultJson insertUser(SysUser user, HttpServletRequest request);

    ResultJson deleteUser(Long userId, HttpServletRequest request);






}
