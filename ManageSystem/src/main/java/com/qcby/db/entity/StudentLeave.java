package com.qcby.db.entity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO 学生请假实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 3:36
 */
public class StudentLeave {
    private long id;
    private long studentUserId;
    private int state;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reason;
    private long arrproveUserId;
    private String noAproveReason;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
