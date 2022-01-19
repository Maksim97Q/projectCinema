package com.cinema.service;

import com.cinema.model.User;

public interface UserService {
    boolean create(User user);
    boolean delete(User user);
    String read(User user);
}
