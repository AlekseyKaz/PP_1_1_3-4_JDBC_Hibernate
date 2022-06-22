package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/user";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    public static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        if (connection == null) {
        }
        try {
            Class.forName(driver);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}





