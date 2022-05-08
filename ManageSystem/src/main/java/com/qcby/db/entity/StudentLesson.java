package com.qcby.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.PrimitiveIterator;

/**
 * TODO 学生上课实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 0:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentLesson {
    private long id;
    private long teacherUserId;
    private String subject;
    private long classId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime creatTime;
    private LocalDateTime updateTime;


}
