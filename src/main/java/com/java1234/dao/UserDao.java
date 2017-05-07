package com.java1234.dao;

import com.java1234.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jarvis on 2017/5/7.
 */
public class UserDao {
    public User getByUserName(Connection con, String userName) throws Exception {
        User              resultUser        = null;
        String            sql               = "select * from t_user where userName=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            resultUser = new User(rs.getInt("id"), rs.getString("userName"),
                    rs.getString("password"));
        }
        return resultUser;
    }

    public Set<String> getRoles(Connection con, String userName) throws SQLException {
        Set<String>       roles             = new HashSet<String>();
        String            sql               = "select * from t_user u,t_roles r where u.roleId=r.id and u.userName=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            roles.add(rs.getString("roleName"));
        }
        return roles;
    }

    public Set<String> getPermissions(Connection con, String userName) throws SQLException {
        Set<String> permissions = new HashSet<String>();
        String sql = "select * from t_user u,t_roles r,t_permission p where u.roleId=r.id " +
                "and p.roleId=r.id and u.userName=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            permissions.add(rs.getString("permissionName"));
        }
        return permissions;
    }
}

