package com.devinwang.gchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName chat_img
 */
@TableName(value ="chat_img")
@Data
public class ChatImg implements Serializable {
    /**
     * 图片表id，也是文章id
     */
    private String id;

    /**
     * 图片地址
     */
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}