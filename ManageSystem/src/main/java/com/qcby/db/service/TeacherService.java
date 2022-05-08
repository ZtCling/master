package com.qcby.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 14:23
 */
public interface TeacherService extends IService<SysUser> {
    List<SysUser> listAll();

    ResultJson insertUser(SysUser user, HttpServletRequest request);

    ResultJson deleteUser(Long userId, HttpServletRequest request);
}
