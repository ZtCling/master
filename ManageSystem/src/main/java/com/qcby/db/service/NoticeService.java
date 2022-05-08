package com.qcby.db.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.AdminNotification;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/10 21:12
 */
public interface NoticeService extends IService<AdminNotification> {
    List<AdminNotification> noticeList(Integer pageIndex, Integer pageSize, Long userId);

    IPage<AdminNotification> listAll(Integer pageIndex, Integer pageSize,
                                     String title,
                                     String content,
                                     String releaseTime);

    int update(String title,
               String content,
               String state,
               String userId);

    int delete(String idList);



    int insert(String title, String content, long userId, Date releaseTime,
               Date createTime, Date updateTime, Integer state);


}
