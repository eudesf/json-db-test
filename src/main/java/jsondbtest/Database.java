package jsondbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        var url = "jdbc:postgresql://localhost/root?user=root&password=root";
        return DriverManager.getConnection(url);
    }
}