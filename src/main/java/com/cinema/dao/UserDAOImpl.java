package com.cinema.dao;

import com.cinema.hibernate.HibernateUtil;
import com.cinema.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users = readAllUsers();

    }

    @Override
    public boolean create(User user) {
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
    public String read(User user) {
        Query<User> query = session.createQuery("FROM User WHERE name=:name AND password=:password", User.class)
                .setParameter("name", user.getName())
                .setParameter("password", user.getPassword());
        if (!query.list().isEmpty())
            return query.getSingleResult().getRole();
        return "";
    }

    @Override
    public List<User> readAllUsers() {
        return users = session.createQuery("FROM User ", User.class).list();
    }

    @Override
    public boolean delete(User user) {
        User user1 = session.get(User.class, user.getId());
        if (user1 != null) {
            session.beginTransaction();
            session.delete(user1);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user) {
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
    }
}
