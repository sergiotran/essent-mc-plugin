package toolkiz.sergio.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;

    public Connection getConnection() throws SQLException {
        if(connection != null) {
            return connection;
        }

        String connectUrl = "jdbc:mysql://localhost:3306/sergio";
        String user = "root";
        String passwd = "1561";

        this.connection = DriverManager.getConnection(connectUrl, user, passwd);

        System.out.println("Connected to database");

        return this.connection;
    }

    public void initializeDatabase() {
        try {
            Statement statement = this.getConnection().createStatement();

            statement.execute("CREATE DATABASE IF NOT EXISTS sergio;");

            System.out.println("Initialized");
        } catch ( SQLException e) {
            System.out.println("Unable to connect database.");
            e.printStackTrace();
        }
    }
}
