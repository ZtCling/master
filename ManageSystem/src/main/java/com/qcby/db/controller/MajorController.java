package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.DicMajorInfo;
import com.qcby.db.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 20:07
 */
@RestController
@RequestMapping("major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @RequestMapping("listAll")
    @PreAuth("major:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看专业信息")
    public ResultJson listAll(String majorName, String majorDescription){
        return majorService.listAll(majorName, majorDescription);
    }

    @RequestMapping("insert")
    @PreAuth("major:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加专业")
    public ResultJson insert(DicMajorInfo dicMajorInfo){
        boolean r = majorService.save(dicMajorInfo);
        return ResultJson.result(r);
    }

    @RequestMapping("delete")
    @PreAuth("major:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除专业")
    public ResultJson delete(@RequestParam List<Long> idList){
        boolean r = majorService.removeByIds(idList);
        return ResultJson.result(r);
    }

    @RequestMapping("update")
    @PreAuth("major:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "更改专业")
    public ResultJson update(DicMajorInfo dicMajorInfo) throws ParseException {
        boolean r = majorService.updateById(dicMajorInfo);
        return ResultJson.result(r);


    }


}
