<%-- 
    Document   : bookingForm
    Created on : Feb 17, 2025, 10:39:54 PM
    Author     : Aruna
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Taxi Booking</title>
    <link rel="stylesheet" href="assets/Booking.css">
</head>
<body>
    <div class="container">
    <h2>Taxi Booking Form</h2>
    <form action="BookingController" method="POST">
    <label for="customerPhone">Phone Number:</label>
    <input type="text" name="customerPhone" required>
    <label for="source">Source Location:</label>
    <input type="text" name="source" required>
    <label for="destination">Destination:</label>
    <input type="text" name="destination" required>
    <label for="vehicleId">Vehicle ID:</label>
    <input type="number" name="vehicleId" required>
    <label for="driverId">Driver ID:</label>
    <input type="number" name="driverId" required>
    <label for="totalAmount">Total Amount:</label>
    <input type="number" name="totalAmount" step="0.01" required>
    <label for="discountCode">Discount Code:</label>
    <input type="text" name="discountCode">
    <button type="submit">Create Booking</button>
</form>
    </div>
</body>
</html>
