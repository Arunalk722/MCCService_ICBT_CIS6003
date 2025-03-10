<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bookingId = request.getParameter("bookingId");
    String finalAmount = request.getParameter("finalAmount");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Booking Successful</title>
    <link rel="stylesheet" href="assets/BookingMessages.css">
</head>
<body>
    <div class="container">
        <h2>Booking Successful!</h2>
        <p>Your booking has been confirmed.</p>
        <p><strong>Booking ID:</strong> <%= bookingId %></p>
        <p><strong>Final Amount (After Discount):</strong> LKR<%= finalAmount %></p>
        
        <a href="BookingController"><button>Make Another Booking</button></a>
        <a href="dashboard.jsp"><button>Go to Dashboard</button></a>
    </div>
</body>
</html>
