package com.qcby.db.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/8 0:59
 */
public interface RoleService extends IService<SysRole> {
    IPage<SysRole> listAll(@Param("roleName") String roleName,
                           @Param("description") String description,
                           IPage<SysRole> page);


    List<SysRole> myRole(Long userId);

    ResultJson setRoleMenu(Long roleId, List<Long> menuIdList);




}
