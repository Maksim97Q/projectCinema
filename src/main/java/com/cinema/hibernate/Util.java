package com.cinema.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Util {
    private final Session session = HibernateUtil.getSessionFactory().openSession();

    //+
    public boolean add(Worker worker) {
        Query query = session.createQuery("SELECT name FROM Worker WHERE name=:name")
                .setParameter("name", worker.getName());
        if (query.list().isEmpty()) {
            session.beginTransaction();
            session.save(worker);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    //+
    public boolean delete(Worker worker) {
        Worker worker1 = session.get(Worker.class, worker.getId());
        if (worker1 != null) {
            session.beginTransaction();
            session.delete(worker1);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    //+
    public int read(Worker worker) {
        Query<Worker> query = session.createQuery("FROM Worker WHERE name=:name AND age=:age", Worker.class)
                .setParameter("name", worker.getName())
                .setParameter("age", worker.getAge());
        return query.getSingleResult().getId();
    }

    //+
    public boolean update(Worker worker) {
        Worker worker1 = session.get(Worker.class, worker.getId());
        if (worker1 != null) {
            session.beginTransaction();
            worker1.setName(worker.getName());
            worker1.setIncome(worker.getIncome());
            session.update(worker1);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    //+
    public List<Worker> workerList1 = new ArrayList<>();

    public List<Worker> readAll() {
        return workerList1 = session.createQuery("FROM Worker", Worker.class).list();
    }
}
