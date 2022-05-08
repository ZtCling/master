package com.qcby.db.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.qcby.db.common.contest.QcbyContext;

import java.util.Calendar;
import java.util.Date;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/7 10:49
 */
public class JwtUtil {
    //过期时间 30min
    private static final int EXPIRE_TIME = 30;
    //私钥
    private static final String TOKEN_SECRET = "privateKey";
    private static final String USER_NAME = "name";

    /**
     *      签发对象：这个用户的id
     *      签发时间：现在
     *      有效时间：30分钟
     *      载荷内容：暂时设计为：这个人的名字
     *      加密密钥：这个人的id加上一串字符串
     * @param userId
     * @param userName
     * @return
     */
    public static String createToken(Long userId, String userName) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,EXPIRE_TIME);
        Date expiresDate = nowTime.getTime();

        String token = JWT.create().withAudience(userId+"")   //签发对象
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate)  //有效时间
                .withClaim(USER_NAME, userName)    //载荷，随便写几个都可以
                .sign(Algorithm.HMAC256(userId+TOKEN_SECRET));   //加密

        QcbyContext.add(token, userId);
        return token;
    }
    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     * @param token
     * @throws
     */
    public static boolean verifyToken(String token, String secret) {
        if (QcbyContext.get(token) == null){
            return false;
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret+TOKEN_SECRET)).build();
            verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            //自定义的一个异常
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 获取签发对象
     */
    public static String getAudience(String token)  {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (Exception j) {
            //这里是token解析失败
            j.printStackTrace();
            return null;
        }
        return audience;
    }
}
