package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.db.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/14 17:01
 */
public interface StudentMapper extends BaseMapper<SysUser> {

    List<SysUser> listAll(@Param("userName") String userName,
                          @Param("userName") String realName,
                          @Param("userName") String sex,
                          @Param("userName") String admissionDate,
                          @Param("userName") String className,
                          @Param("userName") String instituteName,
                          @Param("userName") String majorName);

    int insertUser(@Param("user") SysUser user);
    int insertUserWithRole(@Param("userId") Long userId);

    int deleteUser(@Param("userId") Long userId);
    int deleteUserWithRole(@Param("userId") Long userId);
}
