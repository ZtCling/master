package com.qcby.db.common.contest;

import com.qcby.db.entity.SysUser;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 全局上下文
 *
 * @author ZT
 * <br>CreateDate 2021/9/3 2:07
 */
public class QcbyContext {

    private static Map<String, SysUser> userMap = new HashMap<>();
    private static Map<String, Long> tokenMap = new HashMap<>();


    public static void add(SysUser user) {
        if (user == null) {
            return;
        }
        userMap.put(user.getToken(), user);
    }
    public static SysUser getUser(String token) {
        if (token == null) {
            return null;
        }
        return userMap.get(token);
    }


    public static void add(String token, Long val){
        tokenMap.put(token, val);
    }
    public static Long get(String token){
        return tokenMap.get(token);
    }
    public static void remove(String token){
        Long userId = get(token);
        //删除token信息和用户信息
        userMap.remove(userId);
        tokenMap.remove(token);
    }

}
