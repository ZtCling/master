package com.qcby.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.DicClassInfo;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 15:03
 */
public interface ClassService extends IService<DicClassInfo> {
    ResultJson<DicClassInfo> listAll();
}
