package com.sdac.signup;
import java.io.IOException;
import com.sdac.common.User;
import com.sdac.common.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupController extends HttpServlet {
    private UserDAO userDAO; // Assuming you have a UserDAO implementation

    @Override
    public void init() throws ServletException {
        // Initialize the UserDAO instance
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String role = request.getParameter("role");
        System.out.println(role);
        // Create a new User object
        User newUser = new User(username, password, email, fullname ,role);

        // Add the user to the database
        boolean flag = userDAO.addUser(newUser);

        // Forward the request to the success view (replace "signup-success.jsp" with your desired success view)
        if(flag) {
           request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        else {
        	System.out.println("error.jsp");
    }
    }
}
