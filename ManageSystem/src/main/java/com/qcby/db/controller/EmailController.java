package com.qcby.db.controller;


import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.ToEmail;
import com.qcby.db.entity.SysUser;
import com.qcby.db.service.LoginService;
import com.qcby.db.util.VerCodeGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * TODO 邮箱验证码
 *
 * @author ZT
 * <br>CreateDate 2021/9/13 0:35
 */
@RestController
@RequestMapping("email")

public class EmailController {
    //	引入邮件接口
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private LoginService loginService;


    //	获得发件人信息
    @Value("${spring.mail.username}")
    private String from;
    @RequestMapping("sendEmail")
    @PreAuth("email:send")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "发送邮件")
    public ResultJson commonEmail(ToEmail toEmail, HttpServletRequest request) {
//        创建邮件消息
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);

        message.setTo(toEmail.getTos());

        message.setSubject("您本次的验证码是");

        String verCode = VerCodeGenerateUtil.generateVerCode();

        message.setText("尊敬的xxx,您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");

        mailSender.send(message);
        SysUser user = QcbyContext.getUser(request.getHeader("token"));
        user.setVerCode(verCode);
        return ResultJson.ok("发送成功");
    }
}