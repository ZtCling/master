package com.qcby.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO 专业实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 15:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicMajorInfo {
    private long id;
    private String majorName;
    private String majorDescription;
    private long instituteId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
