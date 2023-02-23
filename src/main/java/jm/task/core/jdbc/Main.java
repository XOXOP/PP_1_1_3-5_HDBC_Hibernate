package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static UserService userServis = new UserServiceImpl();

    public static void main(String[] args) {
        //UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
         UserService userService = new UserServiceImpl();



        // реализуйте алгоритм

        userService.createUsersTable();
        userService.saveUser("Дэн", "Сяопин", (byte) 73);
        userService.saveUser("Дэн", "Браун", (byte) 53);
        userService.saveUser("Мао", "Дзэдун", (byte) 93);
        userService.saveUser("Бен", "Ладен", (byte) 63);

        userService.removeUserById(4);
        userService.getAllUsers();


        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}











