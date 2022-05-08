package com.qcby.db.controller;

import com.qcby.db.anno.PreAuth;
import com.qcby.db.anno.SelfLog;
import com.qcby.db.common.contest.GlobalConstant;
import com.qcby.db.common.web.ResultJson;
import com.qcby.db.entity.DicClassInfo;
import com.qcby.db.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author ZT
 * <br>CreateDate 2021/9/15 15:05
 */
@RestController
@RequestMapping("class")
public class ClassController {
    @Autowired
    private ClassService classService;


    @RequestMapping("listAll")
    @PreAuth("class:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "登录模块")
    public ResultJson listAll(){
        return classService.listAll();
    }


    @RequestMapping("insert")
    @PreAuth("class:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "添加班级")
    public ResultJson insert(DicClassInfo dicClassInfo){
        boolean r = classService.save(dicClassInfo);
        return ResultJson.result(r);
    }


    @RequestMapping("delete")
    @PreAuth("class:delete")
    @SelfLog(type = GlobalConstant.LOG_TYPE_DEL, module = "删除班级")
    public ResultJson delete(@RequestParam List<Long> idList){
        boolean r = classService.removeByIds(idList);
        return ResultJson.result(r);
    }

    @RequestMapping("update")
    @PreAuth("class:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "更改班级")
    public ResultJson update(DicClassInfo dicClassInfo){
        boolean r = classService.updateById(dicClassInfo);
        return ResultJson.result(r);
    }



}
