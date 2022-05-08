package com.qcby.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.DicMajorInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 16:10
 */
public interface MajorService extends IService<DicMajorInfo> {

    ResultJson<DicMajorInfo> listAll(String majorName, String majorDescription);

}
