package com.devinwang.gchat.controller;

import com.alibaba.fastjson.JSON;
import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatUser;
import com.devinwang.gchat.entity.vo.UserInfo;
import com.devinwang.gchat.service.ChatUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class ChatUserController {

    @Autowired
    private ChatUserService chatUserService;
    // @RequestMapping("/saveuser")
    // public R saveUserData(@RequestParam Map<String, String> params) {
    //     String userInfo = params.get("userInfo");
    //     UserInfo user = JSON.parseObject(userInfo, UserInfo.class);
    //
    //     System.out.println(user);
    //     return R.ok();
    // }

    @PostMapping("/saveuser")
    public R saveUserData(@RequestBody UserInfo userInfo) {
        ChatUser chatUser = new ChatUser();
        BeanUtils.copyProperties(userInfo, chatUser);
        chatUserService.saveUser(chatUser);
        return R.ok().message("已经登录成功");
    }
}
