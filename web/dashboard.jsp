<%-- 
    Document   : dashboard
    Created on : Feb 16, 2025, 12:54:32 PM
    Author     : Aruna
--%>

<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.html"); 
        return;
    }
    String role = user.getRole();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="assets/DashBardstyles.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= user.getName() %>!</h2>
        <p>You are logged in as <strong><%= role %></strong>.</p>

        <div class="dashboard-options">
            <% if ("admin".equals(role)) { %>
                <a href="AdminController"><button>Manage Users</button></a>
                <a href="ViewBookingsController"><button>View All Bookings</button></a>
            <% } else if ("operator".equals(role)) { %>                
                <a href="BookingController"><button>New Booking</button></a>
                <a href="ViewBookingsController"><button>View My Bookings</button></a>
            <% } %>
        </div>       

        <a href="LoginController"><button>Logout</button></a>

        <!-- Application version added at the bottom -->
        <div class="app-version">
            <p>Application Version: V1.0.0.3</p>
        </div>
    </div>
</body>
</html>