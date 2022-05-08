package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.constant.ResultJson;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.contest.QcbyContext;
import com.qcby.db.common.web.PageWeb;
import com.qcby.db.entity.AdminNotification;
import com.qcby.db.entity.SysUser;
import com.qcby.db.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * TODO
 * @author ZT
 * <br>CreateDate 2021/9/10 21:15
 */
@RestController
@RequestMapping("notice")
@Api(tags = {"通知模块--tags"})
//日志的两种形式之一
@Slf4j
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation("查看我的通知接口")
    @PreAuth("notice:noticeList")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看我的通知")
    @RequestMapping(value = "noticeList", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public com.qcby.db.common.web.ResultJson noticeList(Integer pageIndex, Integer pageSize, HttpServletRequest request){
        String token = request.getHeader("token");
        SysUser userDb = QcbyContext.getUser(token);
        long userId = userDb.getId();
        String userName = userDb.getUserName();

        List<AdminNotification> list = noticeService.noticeList(pageIndex, pageSize, userId);
        return com.qcby.db.common.web.ResultJson.ok(list);
    }

    @ApiOperation("查看所有通知接口")
    @PreAuth("notice:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看我的通知")
    @RequestMapping(value = "listAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public com.qcby.db.common.web.ResultJson listAll(Integer pageIndex, Integer pageSize,
                                                     String title, String content, String releaseTime){
        return com.qcby.db.common.web.ResultJson.ok(PageWeb.build(noticeService.listAll(pageIndex, pageSize, title, content, releaseTime)));
    }



    @ApiOperation("修改通知接口")
    @PreAuth("notice:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "修改通知")
    @RequestMapping(value = "update", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public com.qcby.db.common.web.ResultJson update(String title, String content, String state, String userId, HttpServletRequest request){
        String token = request.getHeader("token");
        SysUser userDb = QcbyContext.getUser(token);
        String uId = String.valueOf(userDb.getId());
        int count = noticeService.update(title, content, state, uId);
        if (count == ResultJson.MAN){
            return com.qcby.db.common.web.ResultJson.ok();
        }else{
            return com.qcby.db.common.web.ResultJson.error();
        }
    }

    @ApiOperation("删除通知接口")
    @PreAuth("notice:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除通知")
    @RequestMapping(value = "delete", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public com.qcby.db.common.web.ResultJson delete(@RequestParam(value = "idList" ,required=false) String idList){
        int count = noticeService.delete(idList);
        if (count == ResultJson.MAN){
            return com.qcby.db.common.web.ResultJson.ok();
        }else{
            return com.qcby.db.common.web.ResultJson.error();
        }
    }


    @ApiOperation("添加通知接口")
    @PreAuth("notice:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加通知")
    @RequestMapping(value = "insert", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token",
                    value = "token令牌", required = true,
                    dataType = "string", paramType = "header")}
    )
    public com.qcby.db.common.web.ResultJson insert(HttpServletRequest request, String title,
                                                    String content, Long userId, String releaseTime,
                                                    String createTime, String updateTime, Integer state){

        String token = request.getHeader("token");
        SysUser userDb = QcbyContext.getUser(token);
        Long uId = userDb.getId();
        Date rTime = new Date();
        Date cTime = new Date();
        Date uTime = new Date();

        int count = noticeService.insert(title, content, uId, rTime, cTime, uTime, state);
        if (count == ResultJson.MAN){
            return com.qcby.db.common.web.ResultJson.ok();
        }else{
            return com.qcby.db.common.web.ResultJson.error();

        }
    }

}
