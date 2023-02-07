package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao  {

    //Util util = new Util();


        Connection    connection;

    {
        try {
            connection = Util.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void createUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE  IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), lastName VARCHAR(255), age INT)");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
            try (Statement statement = connection.createStatement()) {
                statement.execute("DROP TABLE  IF EXISTS Users");
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    public void saveUser(String name, String lastName, byte age) {


                String query = "INSERT INTO  users(name, lastName,age) VALUES (?, ?, ?)";
                try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                    //preparedStatement.setLong(1, id);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setByte(3, age);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users" +
                " WHERE id =?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public List<User> getAllUsers() {
          List<User> users = new ArrayList<>();

            String sql ="SELECT * FROM users";
            try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()){
                    User user = new User(resultSet.getString("name"),
                            resultSet.getString("lastName"), resultSet.getByte("age"));
                    user.setId(resultSet.getLong("id"));
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return users;
            }





   //// @Override
   // public List<User> getAllUsers() {
     //   return null;
   // }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





   // public UserDaoJDBCImpl() throws ClassNotFoundException, SQLException {
    //}
