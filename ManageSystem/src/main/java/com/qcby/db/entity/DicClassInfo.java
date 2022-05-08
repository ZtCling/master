package com.qcby.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO 班级实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicClassInfo {
    private long id;
    private int period;
    private String className;
    private long majorId;
    private LocalDateTime creatTime;
    private LocalDateTime updateTime;

    //专业名字段
    @TableField(exist = false)
    private String majorName;

    //学院名字段
    @TableField(exist = false)
    private String instituteName;


}
