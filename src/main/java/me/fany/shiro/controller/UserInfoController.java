package me.fany.shiro.controller;

import me.fany.shiro.entity.Userinfo;
import me.fany.shiro.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    /*
            通过username账户从数据库中取出信息

     */
    @GetMapping("/userList")
    @RequiresPermissions("userInfo:view")  // 权限管理
    public Userinfo findUserInfoByUsername(@RequestParam String username) {
        return userInfoService.findByUsername(username);
    }

    /*
        模拟添加用户信息
     */
    @PostMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String addUserInfo(@RequestBody Userinfo userinfo) {
        return "addUserInfo success";
    }

    /*
        模拟删除用户信息
     */
    @DeleteMapping("/userDelete")
    @RequiresPermissions("userInfo:delete")
    public String deleteUserInfo(@RequestBody Userinfo userinfo) {
        return "deleteUserInfo success";
    }

    /*
        无角色验证
     */

    @RequestMapping("/info")
    public String info() {
        return "info success";
    }


    /*
        无角色验证
     */

    @RequestMapping("/{id}")
    public String getUserinfoByid (@PathVariable String id) {
        return "id=" + id + "getUserinfo success";
    }


}
