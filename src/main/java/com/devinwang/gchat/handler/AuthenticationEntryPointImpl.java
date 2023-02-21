package com.devinwang.gchat.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.devinwang.gchat.entity.ChatUser;
import com.devinwang.gchat.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R<ChatUser> chatUserR = new R<>();
        chatUserR.setMsg("认证失败");
        String json = JSON.toJSONString(chatUserR);
        // 处理异常
        WebUtils.renderString(response, json);
    }
}
