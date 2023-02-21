package com.devinwang.gchat.controller;

import com.devinwang.gchat.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("wxapi")
public class LoginController {
    @Autowired
    private LoginService loginService;
    /**
     * 解密用户敏感数据
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取 session_key api，将 code 换成 openid 和 session_key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)
    public Map decodeUserInfo(String encryptedData, String iv, String code) {
        // 登录
        return loginService.login(encryptedData, iv, code);
    }
}
