package com.sdac.productDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id").trim());
        System.out.println(id);
        PrintWriter out = response.getWriter();
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String productName = request.getParameter("product_name");
        double performance = Double.parseDouble(request.getParameter("performance"));
        double usability = Double.parseDouble(request.getParameter("usability"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        String environment = request.getParameter("environment");
        String customerFeedback = request.getParameter("customer_feedback");
        String username = request.getParameter("username");
        

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE product_details SET product_name=?, performance=?, usability=?, cost=?, environment=?, customer_feedback=?, username=?,product_id=? WHERE product_id=?");

            statement.setString(1, productName);
            statement.setDouble(2, performance);
            statement.setDouble(3, usability);
            statement.setDouble(4, cost);
            statement.setString(5, environment);
            statement.setString(6, customerFeedback);
            statement.setString(7, username);
            statement.setInt(8, productId);
            statement.setInt(9, id);

            int rowsUpdated = statement.executeUpdate();
//            if (rowsUpdated > 0) {
//            	request.setAttribute("updateSuccess", true);
//            	response.sendRedirect("Admin.jsp");
//            } else {
//            	request.setAttribute("updateSuccess", false);
//            	response.sendRedirect("Admin.jsp");
//            }

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
