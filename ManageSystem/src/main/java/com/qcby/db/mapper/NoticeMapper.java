package com.qcby.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.AdminNotification;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/10 21:11
 */
public interface NoticeMapper extends BaseMapper<AdminNotification> {
    List<AdminNotification> noticeList(@Param("userId") long userId);

    IPage<AdminNotification> listAll(IPage<AdminNotification> page,
                                    @Param("title") String title,
                                    @Param("content") String content,
                                    @Param("releaseTime") String releaseTime);

    int update(@Param("title") String title,
               @Param("content") String content,
               @Param("state") String state,
               @Param("userId") String userId);

    int delete(@Param("idList") String idList);

    int insert(@Param("title") String title,
               @Param("content") String content,
               @Param("userId") Long userId,
               @Param("releaseTime") Date releaseTime,
               @Param("createTime") Date createTime,
               @Param("updateTime") Date updateTime,
               @Param("state") Integer state);



}
