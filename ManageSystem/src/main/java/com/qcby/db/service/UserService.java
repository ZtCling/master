package com.qcby.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysUser;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 14:28
 */
public interface UserService extends IService<SysUser> {

    List<SysUser> listAll(String realName);

    ResultJson insert(SysUser user);





}
