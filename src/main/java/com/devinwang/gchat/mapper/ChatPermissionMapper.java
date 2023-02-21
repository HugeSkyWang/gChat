package com.devinwang.gchat.mapper;

import com.devinwang.gchat.entity.ChatPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author devin
* @description 针对表【chat_permission】的数据库操作Mapper
* @createDate 2023-02-20 16:05:10
* @Entity com.devinwang.gchat.entity.ChatPermission
*/
public interface ChatPermissionMapper extends BaseMapper<ChatPermission> {
    List<String> selectPermissionByUserId(String id);
}




