package com.qcby.db.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO 登录日志实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/17 19:59
 */
@Data
public class SysLoginLog {
    private Long id;
    private String userName;
    private LocalDateTime loginTime;
    private String loginIp;

}
