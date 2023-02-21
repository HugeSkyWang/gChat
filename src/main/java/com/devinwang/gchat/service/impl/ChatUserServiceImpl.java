package com.devinwang.gchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatUser;
import com.devinwang.gchat.service.ChatUserService;
import com.devinwang.gchat.mapper.ChatUserMapper;
import org.springframework.stereotype.Service;

/**
* @author devin
* @description 针对表【chat_user(用户列表)】的数据库操作Service实现
* @createDate 2022-12-03 17:31:40
*/
@Service
public class ChatUserServiceImpl extends ServiceImpl<ChatUserMapper, ChatUser>
    implements ChatUserService{

    @Override
    public R saveUser(ChatUser chatUser) {
        String openId = chatUser.getOpenid();
        ChatUser byId = this.getById(openId);
        if (byId != null) {
            return R.ok().message("您已经登录成功");
        }
        boolean save = this.save(chatUser);
        if (save) {
            return R.ok().message("您已经登录成功");
        } else {
            return R.error().message("服务器异常");
        }
    }
}




