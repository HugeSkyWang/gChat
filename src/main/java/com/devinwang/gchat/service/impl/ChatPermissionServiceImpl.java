package com.devinwang.gchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devinwang.gchat.entity.ChatPermission;
import com.devinwang.gchat.service.ChatPermissionService;
import com.devinwang.gchat.mapper.ChatPermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author devin
* @description 针对表【chat_permission】的数据库操作Service实现
* @createDate 2023-02-20 16:05:10
*/
@Service
public class ChatPermissionServiceImpl extends ServiceImpl<ChatPermissionMapper, ChatPermission>
    implements ChatPermissionService{

}




