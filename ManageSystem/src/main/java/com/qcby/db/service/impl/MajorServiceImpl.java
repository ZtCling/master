package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.DicMajorInfo;
import com.qcby.db.mapper.MajorMapper;
import com.qcby.db.service.MajorService;
import com.qcby.db.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author DB
 * <br>CreateDate 2021/9/15 16:11
 */
@Service("major")
@Slf4j
@Transactional // 事务
public class MajorServiceImpl extends ServiceImpl<MajorMapper, DicMajorInfo> implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public ResultJson<DicMajorInfo> listAll(String majorName, String majorDescription) {

//        if (StringUtil.isEmpty(majorName) || StringUtil.isEmpty(majorDescription)){
//            return ResultJson.error("不可为空");
//        }
        List<DicMajorInfo> dicMajorInfoList = majorMapper.listAll(majorName, majorDescription);
        return ResultJson.ok(dicMajorInfoList);
    }
}
