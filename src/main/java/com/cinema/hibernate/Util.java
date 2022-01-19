package com.cinema.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;


public class Util {
    private final Session session = HibernateUtil.getSessionFactory().openSession();

    public void add() {
        try {
            session.beginTransaction();
            Worker worker = new Worker(1, "max11", 11, 3000);
            session.save(worker);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void delete() {
        try {
            session.beginTransaction();
            Worker del = session.get(Worker.class, 22);
            session.delete(del);
            session.getTransaction().commit();
        } catch (Exception e) {
//            log.error("не найдет работник");
        } finally {
            session.close();
        }
    }

    public void read(String name) {
        try {
            session.beginTransaction();
            Worker worker = session.get(Worker.class, 23);
            Query query = session.createQuery("from Worker where name=:name");
            query.setParameter("name", name);
            Worker worker1 = (Worker) query.uniqueResult();
            System.out.println(worker1);
            session.getTransaction().commit();
        } catch (NullPointerException e) {
//            log.error("работник не найден");
        } finally {
            session.close();
        }
    }

    public void update() {
        try {
            session.beginTransaction();
            Worker worker = session.get(Worker.class, 23);
            worker.setName("maksim");
            worker.setAge(24);
            session.getTransaction().commit();
        } catch (Exception e) {
//            log.error("работник не найден");
        } finally {
            session.close();
        }
    }
}
