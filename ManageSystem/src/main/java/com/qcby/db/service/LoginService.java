package com.qcby.db.service;

import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 16:30
 */
public interface LoginService {
    ResultJson login(SysUser user, HttpServletRequest request);

    int updatePassword(String passwordOld, String password, String passwordAgain);

    int updatePersonMessage(String sex, String email, String realName,
                            String photo, String userId);

//    List<User> listAllWithRole(Long id);


    List<Long> listRole(@Param("userId") Long userId);



    ResultJson forgetPassword(HttpServletRequest request, String verCode,
                              String password, Long userId);



    ResultJson menuTree(HttpServletRequest request);











}
