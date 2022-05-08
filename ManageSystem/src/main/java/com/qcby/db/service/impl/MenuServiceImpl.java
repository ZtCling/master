package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.entity.SysMenu;
import com.qcby.db.mapper.MenuMapper;
import com.qcby.db.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/14 14:41
 */
@Service("menu")
@Transactional // 事务
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;



}
