package com.qcby.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * TODO 用户实体类
 *
 * @author ZT
 * <br>CreateDate 2021/9/6 11:28
 */
@Data
public class SysUser {

    private long id;
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "性别 0 男  1女")
    private int sex;
    @ApiModelProperty(value = "用户照片")
    private String photo;
    @ApiModelProperty(value = "入学日期")
    private LocalDateTime admissionDate;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "班级id")
    private int studentClassId;
    @ApiModelProperty(value = "学院id")
    private int instituteId;
    @ApiModelProperty(value = "专业id")
    private int majorId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creatTime;
    @ApiModelProperty(value = "最近更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "令牌")
    private String token;

    // 存储权限集合->不映射数据库字段，仅用于存储信息
    @TableField(exist = false)
    private List<String> authList;

    // 用户对应的所有角色id
    @TableField(exist = false)
    private List<Long> roleId;

    //存储验证码字段
    @TableField(exist = false)
    private String verCode;

    //用户班级名称字段
    @TableField(exist = false)
    private String className;

    //用户学院名称字段
    @TableField(exist = false)
    private String instituteName;

    //用户专业名称字段
    @TableField(exist = false)
    private String majorName;




}
