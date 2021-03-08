package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/project";
    static final String USER = "postgres";
    static final String PASS = "123";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
