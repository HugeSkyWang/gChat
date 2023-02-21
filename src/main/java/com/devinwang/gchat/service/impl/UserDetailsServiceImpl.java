package com.devinwang.gchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devinwang.gchat.entity.ChatUser;
import com.devinwang.gchat.entity.security.LoginUser;
import com.devinwang.gchat.mapper.ChatPermissionMapper;
import com.devinwang.gchat.mapper.ChatUserMapper;
import com.devinwang.gchat.service.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ChatUserMapper chatUserMapper;
    @Autowired
    private ChatPermissionMapper chatPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException {
        LambdaQueryWrapper<ChatUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatUser::getOpenid, openId);
        ChatUser chatUser = chatUserMapper.selectOne(wrapper);
        // 如果不能查到数据就通过抛出异常来给出提示
        if (Objects.isNull(chatUser)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 根据用户查询权限信息，添加到LoginUser中
        // ArrayList<String> list = new ArrayList<>(Arrays.asList("user:common"));
        List<String> permissionKeyList = chatPermissionMapper.selectPermissionByUserId(chatUser.getId());

        // 封装成UserDetails对象返回
        return new LoginUser(chatUser, permissionKeyList);
    }
}
