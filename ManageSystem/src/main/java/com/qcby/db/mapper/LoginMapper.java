package com.qcby.db.mapper;

import com.qcby.db.entity.SysMenu;
import com.qcby.db.entity.SysUser;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 16:26
 */
public interface LoginMapper {
    SysUser login(@Param("user") SysUser user);

    int updatePassword(@Param("passwordOld") String passwordOld,
                       @Param("password") String password,
                       @Param("passwordAgain") String passwordAgain);

    int updatePersonMessage(@Param("sex") String sex,
                            @Param("email") String email,
                            @Param("realName") String realName,
                            @Param("photo") String photo,
                            @Param("userId") String userId);

//    List<User> listAllWithRole(@Param("id") Long id);

    List<Long> listRole(@Param("userId") Long userId);

    int forgetPassword(@Param("password") String password,
                       @Param("userId") Long userId);


    List<String> listAuth(@Param("userId") Long userId);



    List<SysMenu> getMenus(@Param("userId") Long userId);






}
