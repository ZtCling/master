package com.qcby.db.aspect;
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
import java.util.Objects;

/**
 * TODO 操作日志切面
 *
 * @author ZT
 * <br>CreateDate 2021/9/17 10:48
 */
@Aspect
@Component
@Slf4j
public class SelfLogAspect {

    @Autowired
    HttpServletRequest request;
    @Autowired
    private OperateService operateService;

    /**
     * @Author liu-miss
     * @Description //TODO 切入点
     **/
    @Pointcut("@annotation(com.qcby.db.anno.SelfLog)")
    public void pointCut(){

    }

    /**
     * @Author liu-miss
     * @Description //TODO 前置通知
     **/
    @Before("pointCut()")
    public void testBefore(JoinPoint joinPoint ){
        log.info("我是前置通知");
    }

    @After("pointCut()")
    public void after(){
        log.info("我是后置通知");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        log.info("我是AfterReturning通知");
    }


    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        log.info("我是AfterThrowing通知");
    }


    @Around("pointCut()")
    public Object around (ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("进入Around通知....前");
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        String token = request.getHeader("token");
        String userName = null;
        if (!StringUtil.isEmpty(token)){
            SysUser userDb = QcbyContext.getUser(token);
            userName = userDb.getRealName();
        }
        SelfLog selfLog = method.getAnnotation(SelfLog.class);
        log.info("注解selfLog：{}", selfLog);

        SysOperateLog sysOperateLog = new SysOperateLog();
        sysOperateLog.setOperateTime(LocalDateTime.now());
        sysOperateLog.setOperateType(selfLog.type());
        sysOperateLog.setUserName(userName);
        sysOperateLog.setOperateModule(selfLog.module());
        boolean b = operateService.save(sysOperateLog);
        log.info("操作日志是否添加成功：{}", String.valueOf(b));


        String nameValue = selfLog.name();
        log.info("nameValue的值为：{}",nameValue);
        String name = null;

        //获取所有参数值
        Object[] objects = joinPoint.getArgs();
        // 获取是所有参数名字
        String[] paramNames =  signature.getParameterNames();
        for (int i =0;i<paramNames.length;i++){
            if (Objects.equals(nameValue,paramNames[i]) && objects[i] != null){
                name = objects[i].toString();
            }
        }
        log.info("name的值为：{}",name);
        /**
         * 进行业务操作
         */

        // 执行目标方法
        Object r  = joinPoint.proceed();
        /**
         * 目标方法执行完毕之后，执行的业务增强操作
         */
        log.info("结束Around通知....后");
        return r;
    }





}
