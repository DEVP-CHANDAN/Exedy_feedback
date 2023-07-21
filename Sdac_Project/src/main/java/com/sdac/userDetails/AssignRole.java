package com.sdac.userDetails;
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

public class AssignRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	String fullname = "";
	String password = "";
	String email = "";
	String username="";
	String role="";

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
		 username = request.getParameter("username");
		 role = request.getParameter("role");
		 System.out.println(username + role);
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE users SET role = ? WHERE username= ?");
			statement.setString(2, username);
			statement.setString(1, role);
			int rs = statement.executeUpdate();
			if(rs>0) {
			response.sendRedirect("UserServlet");	
			}
			}
			
	   catch (Exception e) {
			System.out.println(e);
		}

	}
}

