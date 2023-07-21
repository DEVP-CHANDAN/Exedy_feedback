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

public class ProductDetail extends HttpServlet {
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
        String role = request.getParameter("role");
        System.out.println(role);
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM product_details");
			ResultSet resultSet1 =  statement.executeQuery();;
			  if(!resultSet1.next() && !role.equals("viewer")) {
			  response.sendRedirect("AddProduct.jsp"); }
			 

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
			out.println(".del { background-color: red; float:right;color:white;}");
			out.println(".table{background-color:#2952d0; color:white }");
	        out.println(".table:hover{background:color:#ed236f};");
	        out.println(".btn-primary:hover{backgrounf-color:#ed236f};");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
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
				if(request.getParameter("role").equals("viewer")){
					out.println("<a href=\"ReportGenerate?username="+ resultSet.getString(9)+ "\" class=\"btn btn-primary\">Generate Report</a>");
				}
				else {
				out.println("<a href=\"UserDetail.jsp?id="+ resultSet.getInt("product_id") + "\" class=\"btn btn-primary\">Edit</a>");
				out.println("<a href=\"AddProduct.jsp "+ "\" class=\"btn btn-primary\">Add</a>");
				out.println("<a href=\"DeleteProduct?username=" + resultSet.getString("username") + "&id=" + resultSet.getInt("product_id") + "\" class=\"btn del\">Delete</a>");
				}
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
