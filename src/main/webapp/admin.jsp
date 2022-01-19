<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }

    .table, h2 {
        margin-top: 10px;
        margin-left: 10px;
    }
</style>
<body>

<%
    request.getSession().getAttribute("lists");
%>
<h2>Таблица всех пользователей</h2>
<table class="table" style="width: 20%;">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>password</th>
    </tr>
    <c:forEach var="users" items="${usersList}" varStatus="lp">
        <tr>
            <td><c:out value="${users.id}"/></td>
            <td><c:out value="${users.name}"/></td>
            <td><c:out value="${users.password}"/></td>
            <td><a href="DeleteServlet?id=${lp.index}">удалить</a></td>
        </tr>
    </c:forEach>
<%--    <tr>--%>
<%--        <form action="DeleteServlet" method="post">--%>
<%--            <input type="text" name="id" placeholder="id"--%>
<%--                   required="required"><br> <br>--%>
<%--            <input type="submit" value="delete">--%>
<%--        </form>--%>
<%--    </tr>--%>
</table>
</body>
</html>
