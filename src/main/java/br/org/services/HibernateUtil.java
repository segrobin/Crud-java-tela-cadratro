package br.org.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            Configuration configuration = new Configuration().configure(classloader.getResource("hibernate.cfg.xml"));
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {

            System.err.println("Falha na criação da SessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
