package com.qcby.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * TODO 通知表实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/10 21:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminNotification {
    @TableId(type = IdType.AUTO)
    private long id;
    private String title;
    private String content;
    private long userId;
    private Date releaseTime;
    private Date createTime;
    private Date updateTime;
    private int state;

    public AdminNotification(String title, String content, long userId,
                             Date releaseTime, Date createTime, Date updateTime,
                             int state) {

        this.title = title;
        this.content = content;
        this.userId = userId;
        this.releaseTime = releaseTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
    }

    @TableField(exist = false)
    private String studentUserName;

    @TableField(exist = false)
    private String teacherUserName;

    @TableField(exist = false)
    private String lessonName;

    @TableField(exist = false)
    private String className;



}
