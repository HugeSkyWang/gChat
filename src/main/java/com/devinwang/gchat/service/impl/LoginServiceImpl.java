package com.devinwang.gchat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devinwang.gchat.entity.ChatUser;
import com.devinwang.gchat.entity.ChatUserRole;
import com.devinwang.gchat.entity.security.LoginUser;
import com.devinwang.gchat.service.ChatUserRoleService;
import com.devinwang.gchat.service.ChatUserService;
import com.devinwang.gchat.service.LoginService;
import com.devinwang.gchat.util.HttpRequest;
import com.devinwang.gchat.util.JwtUtil;
import com.devinwang.gchat.util.RedisCache;
import com.devinwang.gchat.util.WxDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ChatUserService chatUserService;
    @Autowired
    private ChatUserRoleService chatUserRoleService;

    @Override
    public Map login(String encryptedData, String iv, String code) {
        System.out.println(encryptedData + "   " + iv + "    " + code);
        Map map = new HashMap();

        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx825685dd56d3e091";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "74c4edbb3c7d3446ee440497c0b7c04f";
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        LambdaQueryWrapper<ChatUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatUser::getOpenid, openid);
        ChatUser account = chatUserService.getOne(wrapper);
        if (Objects.isNull(account)) {
            String result = WxDecode.decryptS5(encryptedData, "UTF-8", session_key, iv);
            JSONObject userInfoJSON = JSONObject.parseObject(result);
            Map userInfo = new HashMap();
            userInfo.put("openId", openid);
            userInfo.put("nickName", userInfoJSON.get("nickName"));
            userInfo.put("gender", userInfoJSON.get("gender"));
            userInfo.put("city", userInfoJSON.get("city"));
            userInfo.put("province", userInfoJSON.get("province"));
            userInfo.put("country", userInfoJSON.get("country"));
            userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
            userInfo.put("unionId", userInfoJSON.get("unionId"));
            map.put("userInfo", userInfo);
            ChatUser newAccount = new ChatUser();
            newAccount.setOpenid(openid);
            newAccount.setNickName((String) userInfoJSON.get("nickName"));
            newAccount.setGender((Integer) userInfoJSON.get("gender"));
            newAccount.setCity((String) userInfoJSON.get("city"));
            newAccount.setAvatarUrl((String) userInfoJSON.get("avatarUrl"));
            chatUserService.save(newAccount);
            // 添加默认权限
            LambdaQueryWrapper<ChatUser> chatUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
            chatUserLambdaQueryWrapper.eq(ChatUser::getOpenid, openid);
            ChatUser chatUser = chatUserService.getOne(chatUserLambdaQueryWrapper);
            ChatUserRole chatUserRole = new ChatUserRole();
            chatUserRole.setUserId(chatUser.getId());
            chatUserRole.setRoleId("1");
            chatUserRoleService.save(chatUserRole);
        } else {
            Map userInfo = new HashMap();
            userInfo.put("openId", openid);
            userInfo.put("nickName", account.getNickName());
            userInfo.put("gender", account.getGender());
            userInfo.put("city", account.getCity());
            userInfo.put("province", account.getProvince());
            userInfo.put("country", account.getCountry());
            userInfo.put("avatarUrl", account.getAvatarUrl());
            map.put("userInfo", userInfo);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(openid, session_key);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录异常");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //authenticate存入redis
        redisCache.setCacheObject("login:" + openid, loginUser);

        // 使用openid生成token
        String jwt = JwtUtil.createJWT(openid);
        map.put("token", jwt);
        map.put("status", 1);
        map.put("msg", "解密成功");
        return map;
    }
}
