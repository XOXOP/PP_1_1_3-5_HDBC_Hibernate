package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public  class Util {

    private Util(){ };
    private static  Connection connection= null;
    private static final String UserName = "root";
    private static final String password = "harakiri681";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/new_schema_hdbc?serverTimezone=Europe/Moscow&useSSL=false";

    public static Connection getConnection() throws ClassNotFoundException {

        try {Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, UserName, password);
            System.out.println("conect is given");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return connection;
    }


    private static SessionFactory sessionFactory ;


    public static SessionFactory getSessionFactory(){


        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();//.configure();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/new_schema_hdbc?useSSL=false&serverTimezone=Europe/Moscow");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "harakiri681");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return sessionFactory;
    }



}
