package com.cinema.dao;

import com.cinema.model.User;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String roleUser = "user";
    private final Connection connection = ConnectionProvider.connection();
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users = readAllUsers();

    }

    @Override
    public boolean create(User user) throws SQLException {
        PreparedStatement stm = connection.prepareStatement
                ("SELECT name FROM users WHERE name=?");
        stm.setString(1, user.getName());
        ResultSet rs = stm.executeQuery();
        if (!rs.next()) {
            PreparedStatement stmt = connection.prepareStatement
                    ("INSERT INTO users (name, password, role) VALUES (?,?,?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, roleUser);
            stmt.execute();
            return true;
        }
        return false;
    }

    @Override
    public String read(User user) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement
                ("SELECT name, password, role FROM users WHERE name=? AND password=?");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getPassword());
        ResultSet rs = stmt.executeQuery();
        String role = null;
        if (rs.next()) {
            role = rs.getString("role");
        }
        return role;
    }

    @Override
    public List<User> readAllUsers() {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT id, name, password FROM users")) {
            ResultSet rs = stmt.executeQuery();
            int get = 0;
            while (rs.next()) {
                users.add(new User());
                users.get(get).setId(rs.getInt("id"));
                users.get(get).setName(rs.getString("name"));
                users.get(get).setPassword(rs.getString("password"));
                get++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        PreparedStatement stmt = connection
                .prepareStatement("SELECT id FROM users WHERE id=?");
        stmt.setInt(1, user.getId());
        ResultSet a = stmt.executeQuery();
        if (a.next()) {
            PreparedStatement stm = connection
                    .prepareStatement("DELETE FROM users WHERE id=?");
            stm.setInt(1, user.getId());
            stm.execute();
            return true;
        }
        return false;
    }

    @Override
    public void update(User user) {

    }
}
