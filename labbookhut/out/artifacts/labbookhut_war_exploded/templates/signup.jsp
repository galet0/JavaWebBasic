<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.2.2017 г.
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <jsp:include page="menu.jsp"/>
    <form method="post">
        <label>Username</label>
        <input type="text" name="username"/>
        <label>Password</label>
        <input type="password" name="password"/>
        <input type="submit" name="signup" value="Sign Up"/>
    </form>

</body>
</html>
