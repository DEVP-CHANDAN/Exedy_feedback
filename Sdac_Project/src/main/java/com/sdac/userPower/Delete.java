package com.sdac.userPower;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Delete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assuming you have the database connection details
        String url = "jdbc:mysql://localhost:3306/login";
        String dbUsername = "root";
        String dbPassword = "";

        // Get the parameters or values needed for deletion from the request

        String username = request.getParameter("username");
        int feedbackId = Integer.parseInt(request.getParameter("id"));

        // SQL queries to delete data from the tables

        String deleteFeedbackQuery = "DELETE FROM feedback WHERE id = ?";
         System.out.println(feedbackId);
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Create prepared statements for each delete query
            PreparedStatement deleteFeedbackStatement = connection.prepareStatement(deleteFeedbackQuery);
            
            // Set the parameter values for each delete statement
;
            deleteFeedbackStatement.setInt(1, feedbackId);
            System.out.println(deleteFeedbackStatement);
             
            // Execute the delete statements

            deleteFeedbackStatement.executeUpdate();

            // Close the statements and the database connection

            deleteFeedbackStatement.close();
            connection.close();

            // Redirect or display a success message
            response.sendRedirect("UserDetail?username=" + username);
        } 
        
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
}
