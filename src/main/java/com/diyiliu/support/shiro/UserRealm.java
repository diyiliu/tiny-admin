package com.diyiliu.support.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

/**
 * Description: UserRealm
 * Author: DIYILIU
 * Update: 2019-05-23 10:41
 */
public class UserRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        if (StringUtils.isEmpty(username)) {
            throw new AccountException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new AccountException("密码不能为空");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                "admin",
                "9b7818badb85defb83950253316ffbbe",
                ByteSource.Util.bytes("6a75262bcb161d22eae1638f4a75bd14"),
                getName());

        return authenticationInfo;
    }

}
