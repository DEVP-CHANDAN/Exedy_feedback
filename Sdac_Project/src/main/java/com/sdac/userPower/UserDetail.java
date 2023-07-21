package com.sdac.userPower;
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

public class UserDetail extends HttpServlet {
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
		String username = request.getParameter("username");
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM product_details");
			ResultSet resultSet = statement.executeQuery();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Product Details</title>");
			out.println(
					"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\">");
			out.println("<style>");
			out.println(".card { margin-bottom: 20px; animation: easeOutAnimation 0.5s ease-out; }");
			out.println(
					"@keyframes easeOutAnimation { 0% { opacity: 0; transform: translateY(-20px); } 100% { opacity: 1; transform: translateY(0); } }");
		       out.println(".table{background-color:#2952d0; color:white }");
		        out.println(".table:hover{background:color:#ed236f}");
		        out.println(".inputTxt{font-weight:bold}");
		        out.println(".btn-dark:hover{background-color:#ed236f;}");
		        out.println(".btn-danger:hover{color:black;}");
		        out.println(".btn-danger{float:right;}");
		        out.println(".btn-primary:hover{background-color:#ed236f;}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
             System.out.println(username);
			while (resultSet.next()) {
                 String username1 = resultSet.getString("username");
				String product_name = resultSet.getString(10);
				out.println("<div class=\"container\">");
				out.println("<div class=\"card\">");
				out.println("<div class=\"card-body\">");
				out.println("<h5 class=\"card-title\">" + product_name + "----" + resultSet.getString(2) + "</h5>");
				out.println("<table class=\"table table-hover\">");
				out.println("<thead>");
				out.println("<tr>");
				out.println("<th>Performance</th>");
				out.println("<th>Usability</th>");
				out.println("<th>Cost</th>");
				out.println("<th>Enviroment impact</th>");
				out.println("<th>Customer Feedback</th>");
				out.println("<th>Username</th>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				out.println("<tr>");
				out.println("<td>" + resultSet.getFloat(4) + "</td>");
				out.println("<td>" + resultSet.getBigDecimal(5) + "</td>");
				out.println("<td>" + resultSet.getString(6) + "</td>");
				out.println("<td>" + resultSet.getString(7) + "</td>");
				out.println("<td>" + resultSet.getString(8) + "</td>");
				out.println("<td>" + resultSet.getString(9) + "</td>");
				out.println("</tr>");
				out.println("</tbody>");
				out.println("</table>");

			    if (username.equals(username1)) {
			        out.println("<a href=\"EditFeedback.jsp?username="+resultSet.getString("username") + "\" class=\"btn btn-primary\"><span class=\"inputTxt\">Edit</span></a>");
			        out.println("<a href=\"Feedback.jsp?username= "+resultSet.getString("username") + "\" class=\"btn btn-primary\"><span class=\"inputTxt\">Give Feedback</span></a>");
			        out.println("<a href=\"DeleteProduct?username= "+resultSet.getString("username") + "\" class=\"btn btn-danger\"><span class=\"inputTxt\">Delete</span></a>");
			    } 
			    else {
			    out.println("<a href=\"Feedback.jsp?username= "+username + "\" class=\"btn btn-primary\"><span class=\"inputTxt\">Give Feedback</span></a>");
			   
			    }
			    out.println("<a href=\"ReportGenerate?username=" + resultSet.getString("username") + "\" class=\"btn btn-primary\" target=\"_blank\"><span class=\"inputTxt\">Generate Report</span></a>");
			    out.println("</div>");
				out.println("</div>");
				out.println("</div>");
			}
				out.println("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
				out.println(
						"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js\"></script>");
				out.println("</body>");
				out.println("</html>");
			out.println("</tbody>");
			out.println("</table>");
			out.println("</div>");
			out.println("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
			out.println(
					"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js\"></script>");
			out.println("</body>");
			out.println("</html>");
			
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
