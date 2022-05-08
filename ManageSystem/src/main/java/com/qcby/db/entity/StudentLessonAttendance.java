package com.qcby.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO 学生考勤实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/16 1:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentLessonAttendance {
    private long id;
    private long classId;
    private long studentUserId;
    private long teacherUserId;
    private long lessonId;
    private int state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;




}
