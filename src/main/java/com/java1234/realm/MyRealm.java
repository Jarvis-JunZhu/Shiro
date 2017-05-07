package com.java1234.realm;

import com.java1234.dao.UserDao;
import com.java1234.entity.User;
import com.java1234.util.DbUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.sql.Connection;

/**
 * Created by Jarvis on 2017/5/7.
 */
public class MyRealm extends AuthorizingRealm {

    private UserDao userDao = new UserDao();
    private DbUtil  dbUtil  = new DbUtil();

    /**
     * 为当前登录成功的用户授予角色和权限
     *
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String                  userName           = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Connection              con                = null;
        try{
            con=dbUtil.getCon();
            authorizationInfo.setRoles(userDao.getRoles(con,userName));
            authorizationInfo.setStringPermissions(userDao.getPermissions(con,userName));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                dbUtil.closeCon(con);
            }catch (Exception e){

            }
        }
        return authorizationInfo;
    }

    /**
     * 验证当前登录的用户
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String     userName = (String) token.getPrincipal();
        Connection con      = null;
        try {
            con = dbUtil.getCon();
            User user = userDao.getByUserName(con, userName);
            if (user != null) {
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(),
                        user.getPassword(), "xx");
                return authcInfo;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
