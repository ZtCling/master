package com.qcby.db.entity;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO 角色实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/8 0:25
 */
@Data
public class SysRole {
    private long id;
    private String roleName;
    private String description;
    private LocalDateTime creatTime;
    private LocalDateTime updateTime;
}
