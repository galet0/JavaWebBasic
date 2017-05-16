<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Issues</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/issues.css"/>
<body>
<jsp:include page="menu.jsp"/>
<br/>
<div class="container">
    <form method="get">
        <div class="row">
            <div class="col-sm-2">
                <div class="form-group">
                    <select name="status"  class="form-control" required>
                        <optgroup label="Status">
                            <option value="" disabled hidden selected>Status</option>
                            <option>All</option>
                            <option>New</option>
                            <option>Solved</option>
                        </optgroup>
                    </select>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="form-group">
                    <input name="name" type="text" class="form-control" placeholder="Search">
                </div>
            </div>
            <div class="col-sm-2">
                <button type="submit" class="btn btn-default">Search</button>
            </div>
        </div>
        <div class="row">
            <a class="btn btn-success" href="${pageContext.request.contextPath}/issues/add">Add Issue</a>
        </div>
    </form>
    <div class="row">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Creation Date</th>
                <th>Author</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${issueViewModels}" var="issue">
                <tr>
                    <td>${issue.id}</td>
                    <td>${issue.name}</td>
                    <td>${issue.status}</td>
                    <td>${issue.priority}</td>
                    <td><fmt:formatDate value="${issue.creationDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${issue.authorUsername}</td>
                    <td>
                        <a href="/issues/edit/${issue.id}" class="btn mini btn-primary">Edit</a>
                    </td>
                    <td>
                        <a href="/issues/delete/${issue.id}" class="confirm-delete mini btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<script  src="${pageContext.request.contextPath}\static\jquery\jquery.min.js"></script>
<script  src="${pageContext.request.contextPath}\static\bootstrap\js\bootstrap.min.js"></script>
<script>
    $("#issues").addClass("active");
</script>
</body>
</html>
