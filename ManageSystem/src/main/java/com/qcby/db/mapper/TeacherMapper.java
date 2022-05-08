package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.db.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 14:21
 */
public interface TeacherMapper extends BaseMapper<SysUser> {
    List<SysUser> listAll();

    int insertUser(@Param("user") SysUser user);
    int insertUserWithRole(@Param("userId") Long userId);

    int deleteUser(@Param("userId") Long userId);
    int deleteUserWithRole(@Param("userId") Long userId);
}
