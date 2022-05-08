package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.db.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO 用户Mapper层
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 14:25
 */
public interface UserMapper extends BaseMapper<SysUser> {
    List<SysUser> listAll(@Param("realName") String realName);

    int insert(@Param("user") SysUser user);


}
