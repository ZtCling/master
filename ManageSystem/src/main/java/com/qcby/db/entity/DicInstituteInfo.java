package com.qcby.db.entity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO 学院实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 20:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicInstituteInfo {
    private long id;
    private String instituteName;
    private String instituteDescription;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
