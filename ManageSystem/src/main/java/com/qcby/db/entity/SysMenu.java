package com.qcby.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO 菜单实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/14 2:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu {
    private long id;
    private String menuName;
    private String menuPermission;
    private String menuPath;
    private long menuParentId;
    private int menuType;
    private int orderNum;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private List<SysMenu> children = new ArrayList<>();

}
