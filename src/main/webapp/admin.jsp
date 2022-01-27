<%@ page import="com.cinema.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cinema.dao.UserDAOImpl" %>
<%@ page import="com.cinema.service.UserServiceImpl" %>
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
    UserDAOImpl userDAO = UserServiceImpl.getUserDAO();
    List<User> users = userDAO.getUsers();
    request.getSession().setAttribute("usersList", users);
%>
<h2>Таблица всех пользователей</h2>
<table class="table" style="width: 30%">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>password</th>
    </tr>
    <c:forEach var="users" items="${sessionScope.usersList}" varStatus="lp">
        <tr>
            <td><c:out value="${lp.index}"/></td>
            <td><c:out value="${users.name}"/></td>
            <td><c:out value="${users.password}"/></td>
            <td><a href="Delete?id=${lp.index}">удалить</a></td>
        </tr>
    </c:forEach>
    <tr>
        <form action="Update" method="post">
            <input type="text" name="id" placeholder="id">
            <input type="text" name="name1" placeholder="update_Name">
            <input type="text" name="password1" placeholder="update_password">
            <input type="submit" value="update">
        </form>
    </tr>
</table>
</body>
</html>
