package com.devinwang.gchat.entity.security;

import com.alibaba.fastjson.annotation.JSONField;
import com.devinwang.gchat.entity.ChatUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private ChatUser chatUser;

    // 存储权限信息
    private List<String> permissions;

    public LoginUser(ChatUser chatUser, List<String> permissions) {
        this.chatUser = chatUser;
        this.permissions = permissions;
    }

    // 存储SpringSecurity所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        // 把permissions中字符串类型的权限信息传换成GrantedAuthority对象存入authorities中
        authorities = permissions.stream().
                        map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return chatUser.getSessionKey();
    }

    @Override
    public String getUsername() {
        return chatUser.getOpenid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
