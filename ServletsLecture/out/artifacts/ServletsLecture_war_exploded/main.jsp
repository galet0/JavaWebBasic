<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 19.2.2017 г.
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<h1>Loop</h1>
    <%! List<String> weekdays;%>
    <% weekdays = (List<String>) request.getAttribute("weekdays");%>
    <% for (String weekday : weekdays) {%>
        <div>
            <%= weekday%>
        </div>
<% } %>
<h2>If statement</h2>
    <%! int readBooks = 1;%>
    <% readBooks++;%>
    <% if(readBooks < 10){%>
        <div>
            <h5><%=readBooks%></h5>
            <h5>You must read more books!</h5>
        </div>
    <%} else {%>
        <div>
            <h5><%=readBooks%></h5>
            <h5>You can take a break!</h5>
        </div>
<%}%>
</body>
</html>
