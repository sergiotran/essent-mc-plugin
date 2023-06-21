package toolkiz.sergio.db;

import toolkiz.sergio.repositories.HomeRepository;
import toolkiz.sergio.repositories.MemberRepository;

import java.sql.*;

public class Database {
    private Connection connection;
    public Database() {
        this.initializeDatabase();
    }

    public Connection getConnection() throws SQLException {
        if(connection != null) {
            return connection;
        }

        String connectUrl = "jdbc:mysql://localhost:3307/";
        String user = "root";
        String passwd = "1561";

        this.connection = DriverManager.getConnection(connectUrl, user, passwd);

        System.out.println("Connected to database");

        return this.connection;
    }

    public void initializeDatabase() {
        try {
            createDatabase();
            // Use database
            queryVoid("use sergio");
            // Init repository
            new MemberRepository(this);
            new HomeRepository(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnectDatabase() {
        this.dropDatabase();
    }

    public void queryVoid(String sql) throws SQLException {
        Statement statement = this.getConnection().createStatement();
        statement.execute(sql);
        statement.close();
    }

    public Object[] query(String sql) throws SQLException {
        Object[] result = new Object[2];
        Statement statement = this.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        result[0] = resultSet;
        result[1] = statement;

        return result;
    }

    public void queryUpdate(String sql) throws SQLException {
        Statement statement = this.getConnection().createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    private void createDatabase() throws SQLException {
        try {
            queryUpdate(
                    "CREATE DATABASE IF NOT EXISTS sergio CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
            );
            System.out.println("Database created");
        } catch (SQLException e) {
            System.out.println("Unable to create database");
            e.printStackTrace();
        }
    }

    private void dropDatabase() {
        try {
            queryVoid("DROP DATABASE sergio");
            this.connection.close();
            System.out.println("[Essent]: Database dropped");
        } catch ( SQLException e) {
            System.out.println("Unable to connect database.");
            e.printStackTrace();
        }
    }
}
