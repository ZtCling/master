package com.qcby.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname RefUserMenu
 * @Description 角色权限实体类
 * @Date 2021/9/13
 * @Created ZT
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefUserMenu {

    private Long roleId;

    private List<Long> menuId;


}
