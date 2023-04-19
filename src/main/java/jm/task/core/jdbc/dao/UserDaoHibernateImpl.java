package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id bigint  AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "lastName VARCHAR(255) NOT NULL," +
                "age TINYINT NOT NULL )";


        try (
                Session session = jm.task.core.jdbc.util.Util.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            tran.commit();

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (HibernateException ex) {
            throw new HibernateException(ex.getMessage());

        }

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP table IF EXISTS users";
        Transaction tran = null;
        try (
                Session session = Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            tran.commit();

        } catch (NullPointerException e) {
            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        } catch (HibernateException ex) {
            throw new HibernateException(ex.getMessage());


        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction tran = null;
        try (
                Session session = Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            tran.commit();

        } catch (NullPointerException e) {
            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        } catch (HibernateException ex) {
            throw new HibernateException(ex.getMessage());

        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction tran = null;
        try (
                Session session = jm.task.core.jdbc.util.Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createNativeQuery("DELETE FROM users WHERE id = :id");
            query.setLong("id", id);
            query.executeUpdate();
            tran.commit();

        } catch (NullPointerException e) {
            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        } catch (HibernateException ex) {
            throw new HibernateException(ex.getMessage());


        }

    }

    @Override
    public List<User> getAllUsers() {
        try (
                Session session = Util.getSessionFactory().openSession()) {
            ;
            List<User> users = session.createNativeQuery(
                    "SELECT * FROM users", User.class).list();


            return users;
        }

    }

    @Override
    public void cleanUsersTable() {
        Transaction tran = null;
        String sql = ("TRUNCATE TABLE users");
        try (
                Session session = Util.getSessionFactory().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();

            tran.commit();

        } catch (NullPointerException e) {

            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        } catch (HibernateException ex) {
            throw new HibernateException(ex.getMessage());


        }
    }
}