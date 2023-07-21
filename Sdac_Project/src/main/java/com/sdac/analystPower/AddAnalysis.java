package com.sdac.analystPower;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddAnalysis extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assuming you have the database connection details
        String url = "jdbc:mysql://localhost:3306/login";
        String dbUsername = "root";
        String dbPassword = "";

        // Get the form data from the request parameters
        String product_id = request.getParameter("productID");
        String product_name = request.getParameter("productName");
        String performance = request.getParameter("performance");
        String usability = request.getParameter("usability");
        String cost = request.getParameter("cost");
        String environment = request.getParameter("environment");
        String customer_feedback = request.getParameter("customer_feedback");
        String username = request.getParameter("username").toLowerCase();
        String pCatagory = request.getParameter("pCatagory");
        String date = request.getParameter("date");
        // Add more parameters as needed

        // SQL query to insert data into the productDetails table
        String insertQuery = "INSERT INTO product_details (performance , usability , cost , environment , customer_feedback , username,product_name,product_id,category,date) VALUES (?, ?, ?,?,?,?,?,?,?,?)";
        // Adjust the query to include additional columns and parameters

        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Create a prepared statement for the insert query
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            // Set the parameter values for the insert statement
            insertStatement.setString(1, performance);
            insertStatement.setString(2, usability);
            insertStatement.setString(3, cost);
            insertStatement.setString(4 , environment);
            insertStatement.setString(5, customer_feedback);
            insertStatement.setString(6,username);
            insertStatement.setString(7, product_name);
            insertStatement.setString(8, product_id);
            insertStatement.setString(9, pCatagory);
            insertStatement.setDate(10, java.sql.Date.valueOf(date));
            
            // Set more parameters as needed

            // Execute the insert statement
            int rowsAffected = insertStatement.executeUpdate();

            // Close the statement and the database connection
            insertStatement.close();
            connection.close();

            if (rowsAffected > 0) {
                // Redirect or display a success message
                response.sendRedirect("Analyst");
            } else {
                // Redirect or display an error message
                response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
