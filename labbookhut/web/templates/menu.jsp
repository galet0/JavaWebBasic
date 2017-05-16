<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bookhut.models.bindingModels.LoginModel" %>
<%@ page import="com.bookhut.config.Config" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 20.2.2017 г.
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="../css/menu.css"/>
</head>
<body>
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/signup">Sign Up</a></li>
        <%
            LoginModel loginModel = (LoginModel) session.getAttribute(Config.LOGIN_MODEL);
            if(loginModel != null){
                String username = loginModel.getUsername();
                request.setAttribute(Config.USERNAME, username);
            }
        %>
        <c:set var="username" value="${USERNAME}"/>
        <c:choose>
            <c:when test="${username != null}">
                <li><a href="/signout">Sign Out(${username})</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="/signin">Sign In</a></li>
            </c:otherwise>
        </c:choose>

        <li><a href="/addbook">Add Book</a></li>
        <li><a href="/shelves">Shelves</a></li>
    </ul>

</body>
</html>
