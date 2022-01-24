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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
    private final UserDAOImpl userDAO = UserServiceImpl.getUserDAO();
    private final UserServiceImpl userService = UserServiceImpl.getUserService();
    private final List<User> users = userDAO.getUsers();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        User id = users.remove(Integer.parseInt(request.getParameter("id")));
        userService.delete(id);
        request.getRequestDispatcher("link.jsp").include(request, response);
        request.getRequestDispatcher("admin.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
