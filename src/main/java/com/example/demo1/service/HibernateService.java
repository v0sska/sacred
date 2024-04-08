package com.example.demo1.service;

import com.example.demo1.entity.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateService {

    private final SessionFactory factory;

    public HibernateService() {
        this.factory = new Configuration()
                .configure()
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();
    }

    public void savePerson(Person person){
        Session session = factory.openSession();
        Transaction transaction = null;
        System.out.println("IN saveNote!");

        try {
            transaction = session.beginTransaction();

            session.save(person); // Використовуємо переданий об'єкт Note

            transaction.commit();
            System.out.println("Save completed!");
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}


