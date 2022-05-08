package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysRole;
import com.qcby.db.mapper.RoleMapper;
import com.qcby.db.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/8 0:59
 */

@Service("role")
@Transactional // 事务
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public IPage<SysRole> listAll(String roleName, String description, IPage<SysRole> page) {
        return roleMapper.listAll(roleName, description, page);
    }

    @Override
    public List<SysRole> myRole(Long userId) {
        return roleMapper.myRole(userId);
    }

    /**
     * 设置角色访问权限，先删除所有，然后逐个插入
     * @param roleId
     * @param menuIdList
     * @return
     */
    @Override
    @Transactional
    public ResultJson setRoleMenu(Long roleId, List<Long> menuIdList) {
        if (roleId == null || menuIdList == null) {
            return ResultJson.error("必填项目不能为空", null);
        }
        roleMapper.deleteAllRoleMenu(roleId);
        roleMapper.insertRoleMenu(roleId, menuIdList);
        return ResultJson.ok("设置权限成功", null);
    }
}

