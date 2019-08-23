package service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    private ConnectionHelper() {
    }

    private static Connection connection;

    public static Connection getMySQLConnection() {
        if (connection == null)
            try {
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

                connection = DriverManager.getConnection(
                    /*new StringBuilder()
                    .append("jdbc:mysql://")
                    .append("localhos:")
                    .append("3306/")
                    .append("db_example?")
                    .append("useSSL=false&")
                    .append("user=root&")
                    .append("password=root")
                    .toString()*/

                        "jdbc:mysql://" +
                                "localhost:" +
                                "3306/" +
                                "db_example?" +
                                "useSSL=false&" +
                                "user=root&" +
                                "password=root"
                );
            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new IllegalStateException();
            }

        return connection;
    }
}
