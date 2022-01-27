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

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private final UserServiceImpl userService = UserServiceImpl.getUserService();
    private final User user = UserServiceImpl.getUser();
    private static final String ADMIN = "admin";
    private static final String USER = "user";

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
            switch (userService.read(user)) {
                case ADMIN:
                    request.getSession().setAttribute("name", user.getName());
                    writer.print("Привет " + user.getName() + ", вы вошли как Администратор");
                    break;
                case USER:
                    request.getSession().setAttribute("name", user.getName());
                    writer.print("Привет " + user.getName() + ", вы вошли как обычный пользователь");
                    break;
                default:
                    writer.print("неверный логин или пароль");
                    request.getRequestDispatcher("login.jsp").include(request, response);
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
        request.getRequestDispatcher("login.jsp").include(request, response);
    }
}
