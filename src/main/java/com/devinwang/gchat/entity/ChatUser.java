package com.devinwang.gchat.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户列表
 * @TableName chat_user
 */
@TableName(value ="chat_user")
@Data
public class ChatUser implements Serializable {

    /**
     * 会员id, 用雪花算法生成
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 微信的openid
     */
    private String openid;

    /**
     * 微信的session_key
     */
    @TableField(value = "session_key")
    private String sessionKey;

    /**
     * 微信昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 性别 0：未知、1：男、2：女
     */
    private Integer gender;

    /**
     * 头像url
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 是否禁用 1 (true) 已禁用，0（false）未禁用
     */
    @TableField(value = "is_disabled")
    private String isDisabled;

    /**
     * 逻辑删除 1 (true)已删除，0（false）未删除
     */
    @TableField(value = "is_deleted")
    private String isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified" ,fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}