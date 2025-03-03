<%-- 
    Document   : viewBookings
    Created on : Feb 18, 2025, 11:47:14 AM
    Author     : Aruna
--%>

<%@ page import="java.util.List" %>
<%@ page import="model.Booking" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin - View Bookings</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/3.3.2/tailwind.min.css">
    <link rel="stylesheet" href="assets/ViewBooking.css">
</head>
<body class="bg-gray-100"> 
<div class="container mx-auto p-4">
    <h2 class="text-2xl font-bold mb-4">Admin Dashboard - Manage Bookings</h2>  
    <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %> 
    <% if (bookings != null && !bookings.isEmpty()) { %>
        <table class="w-full border-collapse shadow-md">
            <thead class="bg-gray-200">
            <tr>
                <th class="py-2 px-4 text-left">Booking ID</th>
                <th class="py-2 px-4 text-left">Customer Phone</th>
                <th class="py-2 px-4 text-left">Source</th>
                <th class="py-2 px-4 text-left">Destination</th>
                <th class="py-2 px-4 text-left">Total Amount</th>
                <th class="py-2 px-4 text-left">Status</th>
                <th class="py-2 px-4 text-left">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (Booking booking : bookings) { %>
                <tr class="hover:bg-gray-50">
                    <td class="py-2 px-4"><%= booking.getBookingId() %></td>
                    <td class="py-2 px-4"><%= booking.getCustomerPhone() %></td>
                    <td class="py-2 px-4"><%= booking.getSource() %></td>
                    <td class="py-2 px-4"><%= booking.getDestination() %></td>
                    <td class="py-2 px-4"><%= booking.getTotalAmount() %> LKR</td>
                    <td class="py-2 px-4"><%= booking.isPaid() ? "Paid" : "Pending" %></td>
                    <td class="py-2 px-4">
                        <form action="CancelBookingController" method="POST">
                            <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                            <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Cancel</button>
                        </form>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p class="text-center text-gray-500 mt-4">No bookings found.</p> 
    <% } %>
    <div class="mt-6 text-center"> 
        <a href="dashboard.jsp"><button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Back to Dashboard</button></a>
    </div>

</div>
</body>
</html>