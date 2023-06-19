package br.org.model;

import br.org.services.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {

    public void saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public User getUserById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            return session.get(User.class, id);
        } finally {
            session.close();
        }
    }

    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            return session.createQuery("FROM User order by id desc", User.class).list();
        } finally {
            session.close();
        }
    }

    public void updateUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
