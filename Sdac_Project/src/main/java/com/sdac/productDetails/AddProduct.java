package com.sdac.productDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connection;

    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/login";
        String jdbcUsername = "root";
        String jdbcPassword = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String productName = request.getParameter("product_name");
        String category = request.getParameter("category");
        String date = request.getParameter("date");
        double performance = Double.parseDouble(request.getParameter("performance"));
        double usability = Double.parseDouble(request.getParameter("usability"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        String environment = request.getParameter("environment");
        String customerFeedback = request.getParameter("customer_feedback");
        String username = request.getParameter("username");

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO product_details (product_name, category, date, performance, usability, cost, environment, customer_feedback, username,product_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");

            statement.setString(1, productName);
            statement.setString(2, category);
            statement.setDate(3, java.sql.Date.valueOf(date));
            statement.setDouble(4, performance);
            statement.setDouble(5, usability);
            statement.setDouble(6, cost);
            statement.setString(7, environment);
            statement.setString(8, customerFeedback);
            statement.setString(9, username);
            statement.setInt(10,productId );

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("Admin.jsp");
            } else {
                out.println("Failed to add product!");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
