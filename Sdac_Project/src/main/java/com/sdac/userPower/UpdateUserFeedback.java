package com.sdac.userPower;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UpdateUserFeedback extends HttpServlet {
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
        String username = (request.getParameter("username"));
        int id = Integer.parseInt(request.getParameter("id"));
        double performance = Double.parseDouble(request.getParameter("performance"));
        double usability = Double.parseDouble(request.getParameter("usability"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        String environment = request.getParameter("environment");
        String customerFeedback = request.getParameter("customer_feedback");


        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE feedback SET  performance=?, usability=?, cost=?, environment=?, feedback=? WHERE id=?");
            statement.setDouble(1, performance);
            statement.setDouble(2, usability);
            statement.setDouble(3, cost);
            statement.setString(4, environment);
            statement.setString(5, customerFeedback);
            statement.setInt(6, id);

            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println(username);
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
