package com.cinema.service;

import com.cinema.dao.UserDAOImpl;
import com.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class UserServiceImpl implements UserService {
    private static final UserDAOImpl userDAO = new UserDAOImpl();
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static final User user = new User();

    public static UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public static User getUser() {
        return user;
    }

    public static UserServiceImpl getUserService() {
        return userService;
    }

    @Override
    public boolean create(User user) {
        try {
            return userDAO.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        try {
            if (userDAO.delete(user))
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String read(User user) {
        try {
            if (userDAO.read(user) != null) {
                return userDAO.read(user);
            } else {
                return "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
