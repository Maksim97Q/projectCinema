package com.cinema.dao;

import com.cinema.hibernate.HibernateUtil;
import com.cinema.hibernate.Worker;
import com.cinema.model.User;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users = readAllUsers();

    }

    @Override
    public boolean create(User user) throws SQLException {
        User user1 = new User();
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setRole("user");
        Query query = session.createQuery("SELECT name, password FROM User WHERE name=:name")
                .setParameter("name", user1.getName());
        if (query.list().isEmpty()) {
            session.beginTransaction();
            session.save(user1);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public String read(User user) throws SQLException {
        Query query = session.createQuery("FROM User WHERE name=:name AND password=:password", User.class)
                .setParameter("name", user.getName())
                .setParameter("password", user.getPassword());
        return ((User) query.getSingleResult()).getRole();
//        PreparedStatement stmt = connection.prepareStatement
//                ("SELECT name, password, role FROM users WHERE name=? AND password=?");
//        stmt.setString(1, user.getName());
//        stmt.setString(2, user.getPassword());
//        ResultSet rs = stmt.executeQuery();
//        String role = null;
//        if (rs.next()) {
//            role = rs.getString("role");
//        }
//        return role;
    }

    @Override
    public List<User> readAllUsers() {
        return users = session.createQuery("FROM User ", User.class).list();
//        try (PreparedStatement stmt = connection.prepareStatement("SELECT id, name, password FROM users")) {
//            ResultSet rs = stmt.executeQuery();
//            int get = 0;
//            while (rs.next()) {
//                users.add(new User());
//                users.get(get).setId(rs.getInt("id"));
//                users.get(get).setName(rs.getString("name"));
//                users.get(get).setPassword(rs.getString("password"));
//                get++;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return users;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        User user1 = session.get(User.class, user.getId());
        if (user1 != null) {
            session.beginTransaction();
            session.delete(user1);
            session.getTransaction().commit();
            return true;
        }
        return false;
//        PreparedStatement stmt = connection
//                .prepareStatement("SELECT id FROM users WHERE id=?");
//        stmt.setInt(1, user.getId());
//        ResultSet a = stmt.executeQuery();
//        if (a.next()) {
//            PreparedStatement stm = connection
//                    .prepareStatement("DELETE FROM users WHERE id=?");
//            stm.setInt(1, user.getId());
//            stm.execute();
//            return true;
//        }
//        return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        User user1 = session.get(User.class, user.getId());
        if (user1 != null) {
            session.beginTransaction();
            user1.setName(user.getName());
            user1.setPassword(user.getPassword());
            session.update(user1);
            session.getTransaction().commit();
            return true;
        }
        return false;
//        PreparedStatement stmt = connection
//                .prepareStatement("SELECT name, password FROM users WHERE id=?");
//        stmt.setInt(1, user.getId());
//        ResultSet rs = stmt.executeQuery();
//        if (rs.next()) {
//            PreparedStatement stm = connection
//                    .prepareStatement("UPDATE users SET name=?, password=? WHERE id=?");
//            stm.setString(1, user.getName());
//            stm.setString(2, user.getPassword());
//            stm.setInt(3, user.getId());
//            stm.execute();
//            return true;
//        }
//        return false;
    }
}
