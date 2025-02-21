<%-- 
    Document   : cancelBooking
    Created on : Feb 17, 2025, 10:40:13 PM
    Author     : Aruna
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cancel Booking</title>
    <link rel="stylesheet" href="assets/Booking.css">
</head>
<body>
    <div class="container">
        <h2>Cancel a Booking</h2>
        <form action="BookingController" method="GET">
            <input type="hidden" name="action" value="cancel">
            
            <label for="bookingId">Enter Booking ID:</label>
            <input type="number" name="bookingId" required>

            <button type="submit">Cancel Booking</button>
        </form>

        <% if (request.getParameter("status") != null) { %>
            <% if ("success".equals(request.getParameter("status"))) { %>
                <p style="color: green;">Booking successfully canceled!</p>
            <% } else { %>
                <p style="color: red;">Error canceling booking.</p>
            <% } %>
        <% } %>
    </div>
</body>
</html>
