package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.DicInstituteInfo;
import com.qcby.db.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 20:31
 */
@RestController
@RequestMapping("institute")
public class InstituteController {
    @Autowired
    private InstituteService instituteService;

    @RequestMapping("listAll")
    @PreAuth("institute:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "查看学院信息")
    public ResultJson listAll(){
        List<DicInstituteInfo> dicInstituteInfoList = instituteService.list();
        return ResultJson.ok(dicInstituteInfoList);
    }


    @RequestMapping("insert")
    @PreAuth("institute:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加学院信息")
    public ResultJson insert(DicInstituteInfo dicInstituteInfo){
        boolean r = instituteService.save(dicInstituteInfo);
        return ResultJson.ok(r);
    }


    @RequestMapping("delete")
    @PreAuth("institute:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除学院信息")
    public ResultJson delete(@RequestParam List<Long> idList){
        boolean r = instituteService.removeByIds(idList);
        return ResultJson.ok(r);
    }

    @RequestMapping("update")
    @PreAuth("institute:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "更改学院信息")
    public ResultJson update(DicInstituteInfo dicInstituteInfo){
        boolean r = instituteService.updateById(dicInstituteInfo);
        return ResultJson.ok(r);
    }

}
