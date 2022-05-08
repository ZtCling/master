package com.qcby.db.util;

import com.qcby.db.entity.SysUser;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @className: JwtUtil
 * @description: 保存用户登录信息
 * @author: lxt
 * @create: 2021-08-17 21:40
 **/
public class JwtUtilBak {

    /**
     * 计划存储所有登录后的用户信息
     * key:    唯一字符串，UUID生成
     * value: 用户信息
     */
    private static Map<String, SysUser> tokenMap = new HashMap<>();

    /**
     * 登录成功，生成token=>  token指一个令牌，代表你已经登录成功
     *
     * @param user
     * @return
     */
    public static String genToken(SysUser user) {
        if (user == null) {
            return null;
        }
        // 通过UUID 生成的一个唯一的字符串
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, user);
        return token;
    }

    public static boolean verToken(String token) {
        if (tokenMap.get(token) == null) {
            return false;
        }
        return true;
    }

    public static SysUser getUser(String token) {
        return tokenMap.get(token);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());

        // k -  v
//        Map<String,String> map1 = new HashMap<>();
//        map1.put("k1","k2");
//        map1.put("k1","k3");
//        map1.put("k2","k3");
//        System.out.println(map1.size());
//        System.out.println(map1.get("k1"));
//
//
//        Map<String,User> mapUser = new HashMap<>();
//        mapUser.put("qcby1",new User("q1"));
//        mapUser.put("qcby2",new User("q2"));
//        mapUser.put("qcby3",new User("q3"));
//
//        System.out.println(mapUser.get("qcby1"));
//        System.out.println(mapUser.get("qcby3"));
//        System.out.println(mapUser.get("qcby4"));
    }
}
