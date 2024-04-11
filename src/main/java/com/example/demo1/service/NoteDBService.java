package com.example.demo1.service;

import com.example.demo1.entity.Note;
import com.example.demo1.interfaces.INoteDBService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class NoteDBService implements INoteDBService {

    private final SessionFactory factory;

    public NoteDBService() {
        this.factory = new Configuration()
                .configure()
                .addAnnotatedClass(Note.class)
                .buildSessionFactory();
    }


    @Override
    public void saveNote(Note note){
        Session session = factory.openSession();
        Transaction transaction = null;
        System.out.println("IN saveNote!");

        try {
            transaction = session.beginTransaction();

            session.save(note); // Використовуємо переданий об'єкт Note

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

    @Override
    public List<String> getNoteName(){
        List<String> names = null;

        Session session = factory.openSession();

        Query<String> noteQuery = session.createQuery("SELECT name FROM Note", String.class);

        names = noteQuery.getResultList();

        session.close();

        return names;
    }

    @Override
    public String getNotePathByName(String name){
        String path = null;

        Session session = factory.openSession();

        Query<String> noteQuery = session.createQuery("SELECT path FROM Note WHERE name = :name ", String.class);
        noteQuery.setParameter("name", name);
        path = String.valueOf(noteQuery.uniqueResult());
        session.close();

        System.out.println(path);
        return path;
    }

    @Override
    public void clearData(){
        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Note").executeUpdate();

        transaction.commit();
        session.close();
    }



    // Перевіряє, чи існує нотатка з вказаним шляхом в базі даних
    @Override
       public boolean noteExists(String path) {
        Session session = factory.openSession();
        boolean exists = false;

        try {
            session.beginTransaction();

            // Створіть HQL-запит для перевірки наявності нотатки з вказаним шляхом
            Query<?> query = session.createQuery("SELECT COUNT(*) FROM Note WHERE path = :path");
            query.setParameter("path", path);
            long count = (long) query.uniqueResult();

            // Якщо кількість результатів більше нуля, нотатка існує
            exists = count > 0;

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return exists;
    }

    @Override
    public Long getCountByGoodRate(){
        Session session = factory.openSession();

        Query<Long> goodRateQuery = session.createQuery("SELECT COUNT(*) FROM Note WHERE rate = 'GOOD'", Long.class);

        Long goodCount = goodRateQuery.getSingleResult();

        session.close();

        return goodCount;
    }

    @Override
    public Long getCountByNormalRate(){
        Session session = factory.openSession();

        Query<Long> normalRateQuery = session.createQuery("SELECT COUNT(*) FROM Note WHERE rate = 'NORMAL'", Long.class);

        Long normalCount = normalRateQuery.getSingleResult(); // Отримуємо результат запиту

        session.close();

        return normalCount;
    }

    @Override
    public Long getCountByBadRate(){
        Session session = factory.openSession();

        Query<Long> badRateQuery = session.createQuery("SELECT COUNT(*) FROM Note WHERE rate = 'BAD'", Long.class);

        Long badCount = badRateQuery.getSingleResult(); // Отримуємо результат запиту

        session.close();

        return badCount;
    }

    @Override
    public List<String> getNoteNameByGoodRate(){
         List<String> names = null;

         Session session = factory.openSession();

         Query<String> nameQuery =session.createQuery("SELECT name FROM Note WHERE rate = 'GOOD' ");

         names = nameQuery.getResultList();

         session.close();

         return names;
    }

    @Override
    public List<String> getNoteNameByNormalRate(){
        List<String> names = null;

        Session session = factory.openSession();

        Query<String> nameQuery =session.createQuery("SELECT name FROM Note WHERE rate = 'NORMAL' ");

        names = nameQuery.getResultList();

        session.close();

        return names;
    }

    @Override
    public List<String> getNoteNameByBadRate(){
        List<String> names = null;

        Session session = factory.openSession();

        Query<String> nameQuery =session.createQuery("SELECT name FROM Note WHERE rate = 'BAD' ");

        names = nameQuery.getResultList();

        session.close();

        return names;
    }

    @Override
    public String getImagePathByName(String name) {
        String imagePath = null;

        Session session = factory.openSession();


        Query<String> noteQuery = session.createQuery("SELECT imagePath FROM Note WHERE name = :name ", String.class);
        noteQuery.setParameter("name", name);
        imagePath = String.valueOf(noteQuery.uniqueResult());
        session.close();

        System.out.println(imagePath);
        return imagePath;
    }


}
