package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {

    private static UserService userServis = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм
        userServis.createUsersTable();
        userServis.saveUser("Дэн", "Сяопин", (byte) 73);
        userServis.saveUser("Дэн", "Браун", (byte) 53);
        userServis.saveUser("Мао", "Дзэдун", (byte) 93);
        userServis.saveUser("Бен", "Ладен", (byte) 63);

        userServis.removeUserById(4);
        userServis.getAllUsers();
        userServis.dropUsersTable();
        userServis.cleanUsersTable();
    }
}











