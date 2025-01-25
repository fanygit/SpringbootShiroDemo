package me.fany.shiro.dao;

import me.fany.shiro.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserInfoDao extends JpaRepository<Userinfo,Long> {
    // 通过username查找用户信息
    Userinfo findByUsername(String username);
}
