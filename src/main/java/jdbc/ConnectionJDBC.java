package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {

        final String userName = "root";
        final String password = "root";
        return getMySQLConnection(userName, password);
    }

    private static Connection getMySQLConnection(String userName, String password) throws SQLException,
            ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        final String connectionURL = "jdbc:mysql://localhost:3306/email?autoReconnect=true&useSSL=false";

        Connection conn = DriverManager.getConnection(connectionURL, userName,password);
        return conn;
    }
}
