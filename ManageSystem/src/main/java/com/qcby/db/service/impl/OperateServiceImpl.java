package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.entity.SysOperateLog;
import com.qcby.db.mapper.OperateMapper;
import com.qcby.db.service.OperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/17 19:46
 */
@Service("admin")
@Slf4j
@Transactional
public class OperateServiceImpl extends ServiceImpl<OperateMapper, SysOperateLog> implements OperateService {

}
