package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.DicClassInfo;
import com.qcby.db.mapper.ClassMapper;
import com.qcby.db.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 15:04
 */
@Service("class")
@Slf4j
@Transactional // 事务
public class ClassServiceImpl extends ServiceImpl<ClassMapper, DicClassInfo> implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public ResultJson<DicClassInfo> listAll() {
        List<DicClassInfo> dicClassInfoList = classMapper.listAll();
        return ResultJson.ok(dicClassInfoList);
    }
}
