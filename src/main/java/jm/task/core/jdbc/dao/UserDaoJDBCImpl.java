package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = getConnection();


    public UserDaoJDBCImpl() {
//
    }

    public void createUsersTable() {
//        String sql = "CREATE TABLE `mydb`.`users` (\n" +
//                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
//                "  `name` VARCHAR(45) NOT NULL,\n" +
//                "  `lastName` VARCHAR(45) NOT NULL,\n" +
//                "  `age` INT NOT NULL,\n" +
//                "                    PRIMARY KEY (`id`))";

        try (Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists users (id bigint not null auto_increment, " +
                    "name varchar(30) not null, lastName varchar(30) not null, age tinyint not null , primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
//        String SQL = "DROP TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table if exists users ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
//        String SQL = "INSERT INTO `mydb`.`users` (name, lastName, age) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into users (name, lastname, age) values (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
//        String SQL = "DELETE FROM `mydb`.`users` WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
//        String SQL = "SELECT * FROM `mydb`.`users`";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setAge(resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));

                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void cleanUsersTable() {
//        String SQL = "DELETE FROM `mydb`.`users`";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from users");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
