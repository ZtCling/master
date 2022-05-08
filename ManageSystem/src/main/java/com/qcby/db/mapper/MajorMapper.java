package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.db.entity.DicMajorInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 16:02
 */
public interface MajorMapper extends BaseMapper<DicMajorInfo> {

    List<DicMajorInfo> listAll(@Param("majorName") String majorName,
                               @Param("majorDescription") String majorDescription);

}
