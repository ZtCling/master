package com.qcby.db.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.AdminNotification;
import com.qcby.db.mapper.NoticeMapper;
import com.qcby.db.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/10 21:13
 */
@Service("notice")
@Transactional // 事务
@Slf4j
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, AdminNotification> implements NoticeService{
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<AdminNotification> noticeList(Integer pageIndex, Integer pageSize, Long userId) {
//        List<AdminNotification> list = noticeMapper.noticeList(userId);
//        return ResultJson.ok(list);
        //PageHelper.startPage(pageIndex, pageSize);
        return noticeMapper.noticeList(userId);
    }

    @Override
    public IPage<AdminNotification> listAll(Integer pageIndex, Integer pageSize,
                                                 String title, String content, String releaseTime) {
        IPage<AdminNotification> page = new Page<>(pageIndex, pageSize);
        return noticeMapper.listAll(page, title, content, releaseTime);
    }

    @Override
    public int update(String title, String content, String state, String userId) {
        return noticeMapper.update(title, content, state, userId);
    }

    @Override
    public int delete(String idList) {
        return noticeMapper.delete(idList);
    }

    @Override
    public int insert(String title, String content, long userId, Date releaseTime,
                      Date createTime, Date updateTime,
                      Integer state) {

        return noticeMapper.insert(new AdminNotification(title, content, userId, releaseTime,
                createTime, updateTime, state));
    }



}
