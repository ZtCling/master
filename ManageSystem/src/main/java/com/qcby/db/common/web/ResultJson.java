package com.qcby.db.common.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: RestultJson
 * @description: 统一后台返回格式
 * @author: lxt
 * @create: 2021-08-23 21:08
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultJson<T> {

    // 返回的状态码
    private int code;

    // 返回的信息提示
    private String msg;

    // 返回的数据
    private Object data;



    @Deprecated
    public static ResultJson resultError(boolean r) {
        if (r) {
            return ok();
        } else {
            return error();
        }
    }

    public static ResultJson result(boolean r) {
        if (r) {
            return ok();
        } else {
            return error();
        }
    }

    public static ResultJson ok() {
        return ok(com.qcby.db.common.constant.ResultJson.OK_MSG, null);
    }

    public static ResultJson ok(Object data) {
        return ok(com.qcby.db.common.constant.ResultJson.OK_MSG, data);
    }

    public static ResultJson ok(String msg, Object data) {
        return new ResultJson(com.qcby.db.common.constant.ResultJson.OK, msg, data);
    }


    public static ResultJson error() {
        return error(com.qcby.db.common.constant.ResultJson.ERROR_MSG, null);
    }

    public static ResultJson error(Object data) {
        return error(com.qcby.db.common.constant.ResultJson.ERROR_MSG, data);
    }

    public static ResultJson error(String msg, Object data) {
        return new ResultJson(com.qcby.db.common.constant.ResultJson.ERROR, msg, data);
    }

}
