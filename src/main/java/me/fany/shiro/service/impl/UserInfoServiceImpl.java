package me.fany.shiro.service.impl;


import me.fany.shiro.dao.UserInfoDao;
import me.fany.shiro.entity.Userinfo;
import me.fany.shiro.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public Userinfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}
