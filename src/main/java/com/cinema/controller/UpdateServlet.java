package com.cinema.controller;

import com.cinema.dao.UserDAOImpl;
import com.cinema.model.User;
import com.cinema.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
    private final UserDAOImpl userDAO = UserServiceImpl.getUserDAO();
    private final UserServiceImpl userService = UserServiceImpl.getUserService();
    private final List<User> users = userDAO.getUsers();
    private final User user = UserServiceImpl.getUser();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        int idIndex = Integer.parseInt(request.getParameter("id"));
        User id = users.get(idIndex);
        user.setId(id.getId());
        User user1 = new User(id.getId(), request.getParameter("name1"), request.getParameter("password1"));
        userService.update(user1);
        users.set(idIndex, user1);
        request.getSession().setAttribute("usersList", users);
        request.getRequestDispatcher("link.jsp").include(request, response);
        request.getRequestDispatcher("admin.jsp").include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("link.jsp").include(request, response);
        request.getRequestDispatcher("admin.jsp").include(request, response);
    }
}
