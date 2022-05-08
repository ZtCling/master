package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcby.db.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/8 0:30
 */
public interface RoleMapper extends BaseMapper<SysRole> {

    IPage<SysRole> listAll(@Param("roleName") String roleName,
                           @Param("description") String description,
                           IPage<SysRole> page);

    List<SysRole> myRole(@Param("userId") Long userId);




    Integer deleteAllRoleMenu(@Param("roleId") Long roleId);

    Integer insertRoleMenu(@Param("roleId") Long roleId, @Param("menuIdList") List<Long> menuIdList);

}
