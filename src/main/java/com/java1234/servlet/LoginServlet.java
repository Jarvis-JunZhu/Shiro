package com.java1234.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jarvis on 2017/5/7.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login doget");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login post");
        String  userName =req.getParameter("userName");
        String  password = req.getParameter("password");
        Subject subject  = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try{
            subject.login(token);
            resp.sendRedirect("success.jsp");
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("errorINfo","用户名或者密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
