package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.entity.DicInstituteInfo;
import com.qcby.db.mapper.InstituteMapper;
import com.qcby.db.service.InstituteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 20:27
 */
@Service("institute")
@Slf4j
@Transactional // 事务
public class InstituteServiceImpl extends ServiceImpl<InstituteMapper, DicInstituteInfo> implements InstituteService {


}
