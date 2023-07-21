package com.sdac.common;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportGenerate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jdbcURL = "jdbc:mysql://localhost:3306/login";
		String username = "root";
		String password = "";
		String username1 = request.getParameter("username");

		try {
			// Establish database connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			// Retrieve product details from the database
			String sqlQuery = "SELECT product_name, performance, usability, cost, environment, customer_feedback FROM product_details WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, username1);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				// Retrieve the required data from the result set
				String productName = resultSet.getString("product_name");
				float performance = resultSet.getFloat("performance");
				float usability = resultSet.getFloat("usability");
				String cost = resultSet.getString("cost");
				String environmentImpact = resultSet.getString("environment");
				String feedback = resultSet.getString("customer_feedback");

				// Create a new PDF document
				PDDocument document = new PDDocument();

				// Create a new page
				PDPage page = new PDPage();
				document.addPage(page);

				// Create a new content stream for the page
				PDPageContentStream contentStream = new PDPageContentStream(document, page);

				// Set the font and font size
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

				// Set the starting position for the text
				float x = 50;
				float y = page.getMediaBox().getHeight() - 50;

				// Add the fields to the PDF document
				contentStream.beginText();
				contentStream.newLineAtOffset(x, y);
				contentStream.showText("Product Name: " + productName);
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Performance: " + performance);
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Usability: " + usability);
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Cost: " + cost);
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Environment Impact: " + environmentImpact);
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Feedback: " + feedback);
				contentStream.endText();

				// Close the content stream
				contentStream.close();

				// Create a ByteArrayOutputStream to hold the PDF content
				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				// Save the document to the ByteArrayOutputStream
				document.save(baos);

				// Set the response content type
				response.setContentType("application/pdf");

				// Set the response headers for file download
				response.setHeader("Content-Disposition", "attachment; filename=product_detail.pdf");

				// Set the response content length
				response.setContentLength(baos.size());

				// Write the PDF content to the response output stream
				response.getOutputStream().write(baos.toByteArray());

				// Close the document
				document.close();
			}

			// Clean up resources
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
