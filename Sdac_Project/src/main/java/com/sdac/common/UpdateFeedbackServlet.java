package com.sdac.common;
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

@WebServlet("/UpdateFeedbackServlet")
public class UpdateFeedbackServlet extends HttpServlet {
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
        String username = request.getParameter("username");
        System.out.println(username);
        double performance = Double.parseDouble(request.getParameter("performance"));
        double usability = Double.parseDouble(request.getParameter("usability"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        String environment = request.getParameter("environment");
        String customerFeedback = request.getParameter("customer_feedback");


        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE product_details SET  performance=?, usability=?, cost=?, environment=?, customer_feedback=? WHERE username=?");
            statement.setDouble(1, performance);
            statement.setDouble(2, usability);
            statement.setDouble(3, cost);
            statement.setString(4, environment);
            statement.setString(5, customerFeedback);
            statement.setString(6, username);

            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0) {
            	response.sendRedirect("UserDetail?username=" + username);
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
