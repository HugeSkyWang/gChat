package com.devinwang.gchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 点赞列表
 * @TableName chat_star
 */
@TableName(value ="chat_star")
@Data
public class ChatStar implements Serializable {
    /**
     * 内容id
     */
    @TableField(value = "content_id")
    private String contentId;

    /**
     * 点赞者id
     */
    @TableField(value = "open_Id")
    private String openId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}