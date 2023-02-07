package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//import static jdk.internal.net.http.HttpConnection.getConnection;

public  class Util {
    private Util(){ };
    private static  Connection connection= null;
    private static final String UserName = "root";
    private static final String password = "harakiri681";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/new_schema_hdbc?serverTimezone=Europe/Moscow&useSSL=false";

    public static Connection getConnection() throws ClassNotFoundException {
        // реализуйте настройку соеденения с БД


        try {Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, UserName, password);
            System.out.println("conect is given");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return connection;
    }
}
