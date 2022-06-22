package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        try (Connection connection = Util.getConnection()) {

            userDao.createUsersTable();
            for (byte i = 1; i < 5; i++) {
                String name = "человек-паук из вселенной - " + i;
                String lastname = "паркет " + i;
                userDao.saveUser(name, lastname, i);
                System.out.println("User с именем " + name + "добавлен в базу данных");
            }

            userDao.getAllUsers().forEach(System.out::println);
//        userDao.dropUsersTable();

//        userDao.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
