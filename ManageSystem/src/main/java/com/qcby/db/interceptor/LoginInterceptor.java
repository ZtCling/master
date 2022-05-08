package com.qcby.db.interceptor;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname LoginInterceptor
 * @Description 登录拦截器
 * @Date 2021/8/19
 * @Created ZT
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 如果不是 controller 的方法，不拦截
         */
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        /**
         * 没有找到该对象，进行拦截
         */
        if (QcbyContext.getUser(token) == null) {
            log.info("验证失败");
            return false;
        }
        SysUser user = QcbyContext.getUser(token);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PreAuth preAuth = handlerMethod.getMethodAnnotation(PreAuth.class);
        /**
         * 空路径不拦截
         */
        if (preAuth == null) {
            log.info("验证通过");
            return true;
        }
        /**
         * 路径不包含，拦截
         */
        if (!user.getAuthList().contains(preAuth.value())) {
            log.info("您无权限");
            return false;
        }
        log.info("验证通过");
        return true;
    }
}
