package com.devinwang.gchat.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName chat_permission
 */
@TableName(value ="chat_permission")
@Data
public class ChatPermission implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO, value = "permission_id")
    private Long permissionId;

    /**
     * 资源权限，如：user:list,user:create
     */
    private String permission;

    /**
     * 删除状态，0：未删除，1：已删除
     */
    private Integer deleted;

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