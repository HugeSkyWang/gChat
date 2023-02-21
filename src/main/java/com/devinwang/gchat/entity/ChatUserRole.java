package com.devinwang.gchat.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName chat_user_role
 */
@TableName(value ="chat_user_role")
@Data
public class ChatUserRole implements Serializable {
    /**
     * 
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 
     */
    @TableField(value = "role_id")
    private String roleId;

    /**
     * 
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}