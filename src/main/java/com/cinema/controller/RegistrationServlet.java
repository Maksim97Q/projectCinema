package com.cinema.controller;

import com.cinema.model.User;
import com.cinema.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
    private final User user = UserServiceImpl.getUser();
    private final UserServiceImpl userService = UserServiceImpl.getUserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        request.getRequestDispatcher("link.jsp").include(request, response);

        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        Optional<String> name = Optional.ofNullable((String) request.getSession().getAttribute("name"));
        if (!name.isPresent()) {
            if (userService.create(user)) {
                request.getSession().setAttribute("name", user.getName());
                request.getSession().setAttribute("role", "user");
                writer.print("Hello " + user.getName());
            } else {
                writer.print("Логин занят, пожалуйста введите другой");
                request.getRequestDispatcher("registration.jsp").include(request, response);
            }
        } else {
            writer.print("Вы уже авторизованы");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("link.jsp").include(request, response);
        request.getRequestDispatcher("registration.jsp").include(request, response);
    }
}
