package com.devinwang.gchat.service;

import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author devin
* @description 针对表【chat_user(用户列表)】的数据库操作Service
* @createDate 2022-12-03 17:31:40
*/
public interface ChatUserService extends IService<ChatUser> {

    R saveUser(ChatUser chatUser);

}
