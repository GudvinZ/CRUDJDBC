<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="service.UserService" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.08.2019
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
<c:set var="users" scope="session" value="${UserService.getInstance().getAllUsers()}"/>
<c:if test="${users.size()>0}">
    <c:if test="${!isAdd}">
        <form action="/add/" method="get">
            <button type="submit">Add User</button>
        </form>
    </c:if>
    <c:if test="${isAdd}">
        <jsp:include page="/jsp/add.jsp"/>
    </c:if>
    <p>
    <form action="/deleteAll/" method="post" style="display: inline">
        Users: <button type="submit">Delete all users</button>
    </form>
    </p>
    <jsp:include page="/jsp/dynamicTableUsers.jsp"/>
</c:if>
<c:if test="${users.size()<1}">
    <p>
        You have no users, add user?
    </p>
    <jsp:include page="/jsp/add.jsp"/>
</c:if>
</body>
</html>
