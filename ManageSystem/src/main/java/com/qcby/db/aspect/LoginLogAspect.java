package com.qcby.db.aspect;

import com.qcby.db.anno.LoginLog;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.entity.SysLoginLog;
import com.qcby.db.entity.SysOperateLog;
import com.qcby.db.entity.SysUser;
import com.qcby.db.service.LoginLogService;
import com.qcby.db.service.OperateService;
import com.qcby.db.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * TODO  登录日志切面
 *
 * @author ZT
 * <br>CreateDate 2021/9/17 20:17
 */
@Aspect
@Component
@Slf4j
public class LoginLogAspect {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private LoginLogService loginLogService;


    /**
     * @Author liu-miss
     * @Description //TODO 切入点
     **/
    @Pointcut("@annotation(com.qcby.db.anno.LoginLog)")
    public void pointCut() {

    }

    /**
     * @Author liu-miss
     * @Description //TODO 前置通知
     **/
    @Before("pointCut()")
    public void testBefore(JoinPoint joinPoint) {
        log.info("我是前置通知");
    }

    @After("pointCut()")
    public void after() {
        log.info("我是后置通知");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        log.info("我是AfterReturning通知");
    }


    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        log.info("我是AfterThrowing通知");
    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("进入Around通知....前");
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        String token = request.getHeader("token");
        String userName = null;
        if (!StringUtil.isEmpty(token)) {
            SysUser userDb = QcbyContext.getUser(token);
            userName = userDb.getRealName();
        }
        LoginLog loginLog = method.getAnnotation(LoginLog.class);
        log.info("注解loginLog：{}", loginLog);

//        SysLoginLog sysLoginLog = new SysLoginLog();
//        sysLoginLog.setUserName(userName);
//        sysLoginLog.setLoginTime(LocalDateTime.now());
//        boolean c = loginLogService.save(sysLoginLog);
//        log.info("登录日志是否添加成功:{}", String.valueOf(c));

        Object r  = joinPoint.proceed();
        /**
         * 目标方法执行完毕之后，执行的业务增强操作
         */
        log.info("结束Around通知....后");
        return r;
    }
}
