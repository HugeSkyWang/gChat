package com.devinwang.gchat.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.devinwang.gchat.commonutils.ResultCode.ERROR;
import static com.devinwang.gchat.commonutils.ResultCode.SUCCESS;

// 统一返回结果的类
@Data
public class R {
    public Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    // 把构造方法私有化
    public R() {
    }

    // 成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(SUCCESS);
        r.setMessage("成功");
        return r;
    }
    // 失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
