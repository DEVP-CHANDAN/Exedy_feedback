package com.sdac.common;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteProduct extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id").trim() );
        String username = request.getParameter("username");

        try {
          PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM feedback WHERE username=?");
            Statement stm = connection.createStatement();
            String sql = "DELETE FROM product_details WHERE product_id = " + id + ";";
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();
            int row = stm.executeUpdate(sql);
            if (rowsDeleted > 0 || row > 0) {
               response.sendRedirect("UserDetail.jsp");
            } else {
                out.println("Failed to delete product!");
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
