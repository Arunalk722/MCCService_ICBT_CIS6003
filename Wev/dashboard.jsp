<%-- 
    Document   : dashboard
    Created on : Feb 16, 2025, 12:54:32 PM
    Author     : Aruna
--%>

<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // ✅ Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.html");  // Redirect to login if not logged in
        return;
    }

    // ✅ Get user role
    String role = user.getRole();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="assets/styles.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= user.getName() %>!</h2>
        <p>You are logged in as <strong><%= role %></strong>.</p>

        <div class="dashboard-options">
            <% if ("admin".equals(role)) { %>
                <!-- ✅ Admin Panel Options -->
                <a href="AdminController"><button>Manage Users</button></a>
                <a href="BookingController"><button>View All Bookings</button></a>
            <% } else if ("operator".equals(role)) { %>
                <!-- ✅ Call Center Operator Options -->
                <a href="BookingForm.html"><button>New Booking</button></a>
                <a href="BookingController"><button>View My Bookings</button></a>
            <% } %>
        </div>

        <a href="LoginController"><button>Logout</button></a>
    </div>
</body>
</html>
