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

public class UserServlet extends HttpServlet {
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
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
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
			out.println(".popup-overlay {");
	        out.println("    display: none;");
	        out.println("    position: fixed;");
	        out.println("    top: 0;");
	        out.println("    left: 0;");
	        out.println("    right: 0;");
	        out.println("    bottom: 0;");
	        out.println("    background-color: rgba(0, 0, 0, 0.5);");
	        out.println("    z-index: 9999;");
	        out.println("}");
	        out.println(".popup-content {");
	        out.println("    display: none;");
	        out.println("    position: fixed;");
	        out.println("    top: 50%;");
	        out.println("    left: 50%;");
	        out.println("    transform: translate(-50%, -50%);");
	        out.println("    background-color: #fff;");
	        out.println("    padding: 20px;");
	        out.println("    border-radius: 5px;");
	        out.println("    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.5);");
	        out.println("    z-index: 10000;");
	        out.println("}");
	        out.println(".popup-content ul {");
	        out.println("    list-style: none;");
	        out.println("    padding: 0;");
	        out.println("}");
	        out.println(".popup-content li {");
	        out.println("    margin-bottom: 10px;");
	        out.println("}");
	        out.println(".table{background-color:#2952d0; color:white }");
	        out.println(".table:hover{background:color:#ed236f}");
	        out.println(".inputTxt{font-weight:bold}");
	        out.println(".btn-dark:hover{background-color:#ed236f;}");
	        out.println(".btn-danger:hover{color:black;}");
	        out.println(".roleform{display:inline}");
	        out.println(".role{margin-left:12px; }");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");

			while (resultSet.next()) {

				out.println("<div class=\"container\">");
				out.println("<div class=\"card\">");
				out.println("<div class=\"card-body\">");
				out.println("<table class=\"table \">");
				out.println("<thead>");
				out.println("<tr>");
				out.println("<th>Username</th>");
				out.println("<th>Password</th>");
				out.println("<th>Email</th>");
				out.println("<th>Role</th>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				out.println("<tr>");
				out.println("<td>" + resultSet.getString(2) + "</td>");
				out.println("<td>" + resultSet.getString(3) + "</td>");
				out.println("<td>" + resultSet.getString(4) + "</td>");
				out.println("<td>" + resultSet.getString(6) + "</td>");
				out.println("</tr>");
				out.println("</tbody>");
				out.println("</table>");
				 out.println("<form action='AssignRole?username = '"+ resultSet.getString("username") + "method='get' class = \"roleform\">");
			        out.println("<input type='radio'  name='role' value='analyst' class='role'><span class='inputTxt'>Analyst</span>");
			        out.println("<input type='radio' name='role' value='user' class='role'><span class='inputTxt'>User</span>");
			        out.println("<input type='radio' name='role' value='admin' class='role'><span class='inputTxt'>Admin</span>");
			        out.println("<input type='hidden' name='username' value='"+resultSet.getString("username") + "'/>");
			        out.println("<button type=\"submit\" class=\"btn btn-dark \" style=\"margin-left:12px;padding:3px\">Ok</button>");
			        out.println("</form>");
			    out.println("<a href=\"DeleteUser?username="+resultSet.getString("username") + "\" class=\"btn btn-danger \" style=\" float:right;\">Delete</a>");
		        out.println("</div>");
			    out.println("</div>");
				out.println("</div>");
				out.println("</div>");
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
			 out.println("<script>");
		        out.println("function showRoles() {");
		        out.println("    var overlay = document.getElementById('popup-overlay');");
		        out.println("    var popup = document.getElementById('popup-content');");
		        out.println("    overlay.style.display = 'block';");
		        out.println("    popup.style.display = 'block';");
		        out.println("}");
		        out.println("function closePopup() {");
		        out.println("    var overlay = document.getElementById('popup-overlay');");
		        out.println("    var popup = document.getElementById('popup-content');");
		        out.println("    overlay.style.display = 'none';");
		        out.println("    popup.style.display = 'none';");
		        out.println("}");
		        out.println("</script>");
			out.println("</html>");
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
