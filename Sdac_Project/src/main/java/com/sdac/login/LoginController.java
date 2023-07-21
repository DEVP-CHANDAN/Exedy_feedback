package com.sdac.login;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO1 userDAO; // Assuming you have a UserDAO implementation

    @Override
    public void init() throws ServletException {
        // Initialize the UserDAO instance
        userDAO = new UserDAO1();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the form data
        String username = request.getParameter("username");
        request.setAttribute("username", username);
        String password = request.getParameter("password");
        // Get the user from the database
        UserLogin user = userDAO.getUserByUsername(username);

        // Check if the user exists and the password matches
        if (user != null && user.getPassword().equals(password) && user.getUsername().equals(username) && user.getRole().equals("user")) {
            // User is authenticated, redirect to the success page (replace "login-success.jsp" with your desired success page)
        	response.sendRedirect("UserDetail?username=" + username);  	
        } 
        else if(user != null && user.getPassword().equals(password) && user.getUsername().equals(username) && user.getRole().equals("admin")) {
        	response.sendRedirect("Admin.jsp?role=" + user.getRole());
        }
        else if(user != null && user.getPassword().equals(password) && user.getUsername().equals(username) && user.getRole().equals("Analyst")) {
        	response.sendRedirect("Analyst");
        }
        else {
        	response.sendRedirect("error.jsp");
        }
    }
}
