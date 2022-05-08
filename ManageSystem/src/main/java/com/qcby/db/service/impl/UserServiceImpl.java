package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.constant.ResultJson;
import com.qcby.db.entity.SysUser;
import com.qcby.db.mapper.UserMapper;
import com.qcby.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 14:28
 */

@Service("user")
@Transactional // 事务
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {
    @Autowired
    //@Resource
    private UserMapper userMapper;

    @Override
    public List<SysUser> listAll(String realName) {
        return userMapper.listAll(realName);
    }

    @Override
    public com.qcby.db.common.web.ResultJson insert(SysUser user) {
        int count = userMapper.insert(user);
        if (count == ResultJson.MAN){
            return com.qcby.db.common.web.ResultJson.ok("添加成功", null);
        }else{
            return com.qcby.db.common.web.ResultJson.error("添加失败", null);
        }
    }

}
