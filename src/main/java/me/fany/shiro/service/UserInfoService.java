package me.fany.shiro.service;

import me.fany.shiro.entity.Userinfo;

public interface UserInfoService {
    // 通过username 查找用户信息
    public Userinfo findByUsername(String username);
}
