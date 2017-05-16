<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--<c:set var="numberOfBeers" value="${5}"/>--%>
    <%--<c:out value="${numberOfBeers}"></c:out>--%>

    <c:set var="myWeekdays" value="${weekdays}"/>
    <c:forEach items="${myWeekdays}" var="weekday">
        <div style="color: darkviolet">
            <c:out value="${weekday}"></c:out>
        </div>
    </c:forEach>

    <form method="post">
        <input type="submit" name="click" value="click"/>
    </form>

    <c:choose>
        <c:when test="${5 > 6}">
            <div>5 > 6</div>
        </c:when>
        <c:otherwise>
            <div>5 is lower than 6</div>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="0" end="10" step="1" var="myNumber">
        <c:out value="${myNumber}"></c:out>
    </c:forEach>
</body>
</html>
