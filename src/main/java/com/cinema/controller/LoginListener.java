package com.cinema.controller;

import com.cinema.dao.UserDAOImpl;
import com.cinema.model.User;
import com.cinema.service.UserServiceImpl;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.List;

@WebListener
public class LoginListener implements HttpSessionAttributeListener {
    private final UserDAOImpl userDAO = UserServiceImpl.getUserDAO();
    public List<User> users = userDAO.getUsers();

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        users.add((User) session.getAttribute("usersList"));
        session.setAttribute("usersList", users);
    }

//    @Override
//    public void attributeRemoved(HttpSessionBindingEvent se) {
//        HttpSession session = se.getSession();
//        users.remove(session.getAttribute("usersList"));
//    }
}
