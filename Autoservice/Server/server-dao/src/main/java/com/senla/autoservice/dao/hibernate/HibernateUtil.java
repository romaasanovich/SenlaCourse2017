package com.senla.autoservice.dao.hibernate;

import com.senla.autoservice.bean.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    ////TODO передалать это гумно
    private static HibernateUtil instance;
    private static SessionFactory sessionFactory = null;

    public static HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

  /*  public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            final Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Master.class).addAnnotatedClass(Place.class).addAnnotatedClass(Work.class).addAnnotatedClass(Order.class);
            final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }*/


}



