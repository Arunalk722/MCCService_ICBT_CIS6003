<%-- 
    Document   : bookingForm
    Created on : Feb 17, 2025, 10:39:54 PM
    Author     : Aruna
--%>

<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Taxi Booking</title>
    <link rel="stylesheet" href="assets/Booking.css">
</head>
<body>
    <div class="container">
        <h2>Taxi Booking Form</h2>
        <c:if test="${empty vehicleList}">
        <p style="color: red;">No vehicles available for selection.</p>
        </c:if>
        <form action="BookingController" method="POST">
            <label for="customerPhone">Phone Number:</label>
            <input type="text" name="customerPhone" required>

            <label for="source">Source Location:</label>
            <input type="text" name="source" required>

            <label for="destination">Destination:</label>
            <input type="text" name="destination" required>           

           <label for="vehicleId">Vehicle:</label>
            <select id="vehicleId" name="vehicleId" required>
                <option value="" disabled selected>Select a vehicle</option>
                <c:forEach var="vehicle" items="${vehicleList}">
                    <option value="${vehicle.vehicleId}">
                        ${vehicle.vehicleNumber} (${vehicle.vehicleType})
                    </option>
                </c:forEach>
            </select>
            <label for="driverId">Driver:</label>
            <select id="driverId" name="driverId" required>
                <option value="" disabled selected>Select a driver</option>
                <c:forEach var="driver" items="${driverList}">
                    <option value="${driver.driverId}">
                        ${driver.name} (${driver.licenseNumber})
                    </option>
                </c:forEach>
            </select>
            
            <label for="totalAmount">Total Amount:</label>
            <input type="number" id="totalAmount" name="totalAmount" step="0.01" required>
            
            <label for="discountCode">Select Discount:</label>
            <select name="discountCode">
                <option value="">No Discount</option>
                <%
                    List<String> discountList = (List<String>) request.getAttribute("discountList");
                    if (discountList != null) {
                        for (String discount : discountList) {
                            String code = discount.split(" ")[0];
                %>
                <option value="<%= code %>"><%= discount %></option>
                <%
                 }
                }
                %>
            </select>

            <button type="submit">Create Booking</button>
        </form>
    </div>
</body>
</html>