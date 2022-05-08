package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.entity.SysLoginLog;
import com.qcby.db.mapper.LoginLogMapper;
import com.qcby.db.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/17 20:03
 */
@Service("log")
@Slf4j
@Transactional
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, SysLoginLog> implements LoginLogService {
}
