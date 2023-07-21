<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Feedback Data</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

    <style>
        .card { margin-bottom: 20px; animation: easeOutAnimation 0.5s ease-out; }
        @keyframes easeOutAnimation { 0% { opacity: 0; transform: translateY(-20px); } 100% { opacity: 1; transform: translateY(0); } }
    </style>
</head>
<body>
    <h1 class="text-center">Feedback Data</h1>
    <% 
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/login";
        String username = "root";
        String password = "";
        String user = request.getParameter("username");

        try {
            // Establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create a statement
            Statement statement = conn.createStatement();

            // Execute the query to retrieve all feedback data
            String sql = "SELECT * FROM feedback";
            ResultSet resultSet = statement.executeQuery(sql);

            // Iterate over the result set and display the feedback data in cards
            while (resultSet.next()) {
               String feedback  = resultSet.getString("Feedback");
               String performance = resultSet.getString("performance");
               String usability = resultSet.getString("usability");
               String cost = resultSet.getString("cost");
               String user1 = resultSet.getString("username");
               String environment = resultSet.getString("environment");
                %>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <table class="table table-hover table-dark">
                    <thead>
                        <tr>
                            <th>Performance</th>
                            <th>Usability</th>
                            <th>Cost</th>
                            <th>Environment Impact</th>
                            <th>Customer Feedback</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                         <td><%= resultSet.getFloat(2) %></td>
                            <td><%= resultSet.getFloat(3) %></td>
                            <td><%= resultSet.getFloat(4) %></td>
                            <td><%= resultSet.getString(6) %></td>
                            <td><%= resultSet.getString(1) %></td>
                        </tr>
                    </tbody>
                </table>
                <%
                   if(user.equals(user1)){
                %>
              <a href="EditUserFeedback.jsp?username=<%=resultSet.getString("username") %>&id=<%=resultSet.getInt("id") %>" class="btn btn-primary">Edit</a>
              
			  <a href="Delete?username=<%=resultSet.getString("username")%>&id=<%=resultSet.getInt("id") %>" class="btn btn-primary">Delete</a>
		        <%
			        }
			        %>
            </div>
        </div>
    </div>
</body>
</html>
                <%
            }

            // Close the resources
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>

