package com.qcby.db.util;

/**
 * TODO 字符串工具类
 *
 * @author ZT
 * <br>CreateDate 2021/9/3 3:09
 */
public class StringUtil {


    /**
     * @return [str]
     * @Author liu-miss
     * @Description //TODO 判断字符串是否为空字符串或NULL；
     * @Date l 2021/9/3
     * @Param boolean
     **/
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static void main(String[] aa) {
        System.out.println(isEmpty(""));
        System.out.println(isEmpty("    "));
        System.out.println(isEmpty(null));
        System.out.println(isEmpty("123"));

    }

}
