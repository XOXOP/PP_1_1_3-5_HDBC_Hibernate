package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    //private  static SessionFactory session = null;
   //private final SessionFactory sessionFactory = ;
   private SessionFactory sessionFactory = jm.task.core.jdbc.util.Util.getSessionFactory();
    //private S session;

    //private static Session session;
    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE Users (" +
                "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,name VARCHAR(30) NOT NULL," +
                "lastName VARCHAR(30) NOT NULL,age INT(3) NOT NULL )";
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        if (session!=null) {

            session.getTransaction().commit();
        }else {
            session.getTransaction().rollback();
        }
        session.close();
    }


    @Override
    public void dropUsersTable() {
        String sql = "DROP table IF EXISTS Users";
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        if (session!=null) {

            session.getTransaction().commit();
        }else {
            session.getTransaction().rollback();
        }
        session.close();
    }

//        session = Util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createSQLQuery(sql);
//        try {
//            query.executeUpdate();
//            transaction.commit();
//        } catch (Exception e){
//            transaction.rollback();
//        }
//        session.close();
//    }
//
    @Override
    public void saveUser(String name, String lastName, byte age) {
//        Session session = Util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        try {
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Override
    public void removeUserById(long id) {
//        Session session = Util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("delete from User where id= :id");

        String sql = "delete from Users where id= :id";
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);

       query.setLong("id", id);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select e from User  e";
        Session session = sessionFactory.openSession();
        //session.beginTransaction();

//        Session session = Util.getSessionFactory().openSession();
      Query<User> query  = session.createQuery(sql);
     List<User> list = query.list();
       session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
//        Session session = Util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
        String sql = "delete from Users";
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
//        Query query = session.createQuery("delete from User");
        try {
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
    }
}