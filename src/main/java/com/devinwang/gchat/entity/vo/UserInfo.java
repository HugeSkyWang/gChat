package com.devinwang.gchat.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String openId;
    // 6
    private String avatarUrl;
    // 4
    private String city;
    private String country;
    // 2
    private Integer gender;
    // 3
    private String language;
    // 1
    private String nickName;
    // 5
    private String province;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
