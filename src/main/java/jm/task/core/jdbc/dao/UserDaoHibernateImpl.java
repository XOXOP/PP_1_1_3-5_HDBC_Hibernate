package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {


   private SessionFactory sessionFactory;



    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE Users (" +
                "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,name VARCHAR(30) NOT NULL," +
                "lastName VARCHAR(30) NOT NULL,age INT(3) NOT NULL )";
        Transaction tran = null;
        try(
                Session session = jm.task.core.jdbc.util.Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            tran.commit();
        } catch (RuntimeException e){

            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP table IF EXISTS Users";
        Transaction tran = null;
        try(
                Session session = Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
             query.executeUpdate();
            tran.commit();
        } catch (RuntimeException e){

            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction tran = null;
        try(
                Session session = Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            tran.commit();
        } catch (RuntimeException e){

            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {

        String sql = "delete from Users where id= :id";
        Transaction tran = null;
        try(
                Session session = jm.task.core.jdbc.util.Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.setLong("id", id);
            query.executeUpdate();
            tran.commit();
        }catch (RuntimeException e){

                if (tran != null) {
                    tran.rollback();
                }
                e.printStackTrace();
            }

    }

    @Override
    public List<User> getAllUsers() {
        try(
            Session session = Util.getSessionFactory().openSession()){;
            List<User> users = session.createNativeQuery(
                    "SELECT * FROM Users", User.class).list();


            return users;
        }

    }

    @Override
    public void cleanUsersTable() {
        Transaction tran = null;
        String sql = ("TRUNCATE TABLE Users");
        try (
                Session session = Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            //Session session = sessionFactory.openSession();
            //  session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();

            tran.commit();
        } catch (RuntimeException e) {

            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        }
    }
}