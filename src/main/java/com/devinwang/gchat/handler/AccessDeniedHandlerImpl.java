package com.devinwang.gchat.handler;

import com.alibaba.fastjson.JSON;
import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R result = new R().message("权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);

    }
}
