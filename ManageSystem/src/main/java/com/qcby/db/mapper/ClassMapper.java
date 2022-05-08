package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.db.entity.DicClassInfo;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 14:59
 */
public interface ClassMapper extends BaseMapper<DicClassInfo> {
    List<DicClassInfo> listAll();

}
