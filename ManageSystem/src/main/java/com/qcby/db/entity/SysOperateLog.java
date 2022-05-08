package com.qcby.db.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO 操作日志
 *
 * @author ZT
 * <br>CreateDate 2021/9/17 17:30
 */
@Data

public class SysOperateLog {


    private Long id;
    private int operateType;
    private String operateModule;
    private String userName;
    private LocalDateTime operateTime;

}
