package com.sdac.userPower;
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

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection connection;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		 String feedback = request.getParameter("customer_feedback");
		 String username = request.getParameter("username");
		 String performance  = request.getParameter("performance");
		 String usability = request.getParameter("usability");
		 String cost = request.getParameter("cost");
		 String environment = request.getParameter("environment");
		 String DB_URL = "jdbc:mysql://localhost:3306/login";
		 System.out.println(username);
			String DB_USERNAME = "root";
			String DB_PASSWORD = "";
	        // Try-with-resources to automatically close the resources
	        try  {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO feedback (username, Feedback ,performance,usability,cost,environment) VALUES (?, ?,?,?,?,?)");
	            // Set the parameters
	            statement.setString(1, username); // Assuming you have a specific user ID or use a different mechanism to retrieve it
	            statement.setString(2, feedback);
	            statement.setString(3, performance);
	            statement.setString(4, usability);
	            statement.setString(5, cost);
	            statement.setString(6, environment);
	            // Execute the query
	            int a = statement.executeUpdate();
	            if(a>0) {
                   response.sendRedirect("DisplayFeedback.jsp?username="+username);          
	            }
	        } catch (Exception e) {
	            // Handle any errors that may occur during database connection or query execution
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
