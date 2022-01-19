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
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/Profile")
public class ProfileServlet extends HttpServlet {
    private final UserDAOImpl userDAO = UserServiceImpl.getUserDAO();
    private final UserServiceImpl userService = UserServiceImpl.getUserService();
    private final User user = UserServiceImpl.getUser();
    private final List<User> users = userDAO.getUsers();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("link.jsp").include(request, response);

        String name = (String) request.getSession().getAttribute("name");
        Optional<String> optional = Optional.ofNullable(name);
        if (optional.isPresent()) {
            switch (userService.read(user)) {
                case "admin":
                    request.getSession().setAttribute("usersList", users);
                    request.getRequestDispatcher("admin.jsp").include(request, response);
                    break;
                case "user":
                    out.print("Hello " + name + ", you entered your profile");
            }
        } else {
            out.print("Для начала авторизуйтесь");
        }
        out.close();

    }
}
