package com.sdac.login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO1 {
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/login";
    private final String usernam = "root";
    private final String password = "";

    public UserLogin getUserByUsername(String username) {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection(jdbcUrl, usernam, password);
        	String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                return new UserLogin(username, password , role);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle any potential exceptions here
        }

        return null;
    }
}
