<%-- 
    Document   : verifyPayment
    Created on : Feb 24, 2025, 8:41:57 PM
    Author     : Aruna
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%   
    String bookingId = request.getParameter("bookingId");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Verify Payment</title>
    <script src="assets/script.js"></script>
</head>
<body>
    <h2>Verify Your Booking</h2>
    <form action="VerifyPaymentController" method="POST">
        <label for="bookingId">Booking ID:</label>
        <input type="text" name="bookingId" id="bookingId" value="<%= bookingId != null ? bookingId : "" %>" required readonly>

        <label for="customerPhone">Phone Number:</label>
        <input type="text" name="customerPhone" required>

        <button type="submit">Verify</button>
    </form>
</body>
</html>
