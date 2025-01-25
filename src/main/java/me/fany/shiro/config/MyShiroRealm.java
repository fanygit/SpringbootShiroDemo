package me.fany.shiro.config;


import me.fany.shiro.entity.SysPermission;
import me.fany.shiro.entity.SysRole;
import me.fany.shiro.entity.Userinfo;
import me.fany.shiro.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 进入这里就说明通过验证了
        Userinfo userInfo = (Userinfo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        for (SysRole role:userInfo.getRoles()){
            simpleAuthorizationInfo.addRole(role.getName());
            for (SysPermission permission:role.getPermissions()){
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户输入的账户
        String  username = (String) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());

        // 通过username从数据库中查找UserInfo对象
        Userinfo userinfo = userInfoService.findByUsername(username);
        if (userinfo == null) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userinfo, userinfo.getPassword(), ByteSource.Util.bytes(userinfo.getSalt()), this.getName());
        return simpleAuthenticationInfo;
    }
}
