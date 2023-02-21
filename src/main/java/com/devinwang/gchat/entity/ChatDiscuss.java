package com.devinwang.gchat.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.Value;

/**
 * 内容信息
 *
 * @TableName chat_discuss
 */
@TableName(value = "chat_discuss")
@Data
public class ChatDiscuss implements Serializable {
    /**
     * 发布者id
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 内容id
     */
    @TableId
    @TableField(value = "content_id")
    private String contentId;

    /**
     * 发布内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 点击数
     */
    private Integer hits;

    /**
     * 发布时间
     */
    @TableField(fill = FieldFill.INSERT, value = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}