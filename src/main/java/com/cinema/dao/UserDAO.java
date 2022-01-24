package com.cinema.dao;

import com.cinema.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    boolean create(User user) throws SQLException;

    String read(User user) throws SQLException;

    List<User> readAllUsers() throws SQLException;

    boolean delete(User user) throws SQLException;

    boolean update(User user) throws SQLException;
}
