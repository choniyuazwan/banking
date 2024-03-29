package jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/banking";
    static final String USER = "root";
    static final String PASS = "aaaa";

    static Connection connection;

    public static Connection getConnection() {
        if (connection==null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
