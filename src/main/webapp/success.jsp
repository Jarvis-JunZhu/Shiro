<%--
  Created by IntelliJ IDEA.
  User: Jarvis
  Date: 2017/5/7
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
欢迎你！${userName};
<shiro:hasRole name="admin">欢迎有admin角色的用户！<shiro:principal/></shiro:hasRole>
<shiro:hasPermission name="student:create">欢迎有Student的权限的人登录<shiro:principal/></shiro:hasPermission>
</body>
</html>
