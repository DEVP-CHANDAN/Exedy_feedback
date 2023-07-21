package com.sdac.analystPower;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class DeleteAnalysis extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assuming you have the database connection details
        String url = "jdbc:mysql://localhost:3306/login";
        String dbUsername = "root";
        String dbPassword = "";

        // Get the parameters or values needed for deletion from the request
        String username =request.getParameter("username");



        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            Statement stm = connection.createStatement();
            String sql = "DELETE FROM product_details WHERE username = '" + username.trim() + "'";

            stm.executeUpdate(sql);
            

            // Redirect or display a success message
            response.sendRedirect("Analyst");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
