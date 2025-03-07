<%-- 
    Document   : admin
    Created on : Mar 7, 2025, 11:54:59 PM
    Author     : Aruna
--%>

<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");  // Fetch the user list from the request attribute
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - User Management</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/3.3.2/tailwind.min.css">
    <link rel="stylesheet" href="assets/admin.css">
</head>
<body class="bg-gray-100">

<div class="container mx-auto p-4">
    <h2 class="text-2xl font-bold mb-4">Admin Dashboard - User Management</h2>  
    
    <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %> 
    
    <% if (users != null && !users.isEmpty()) { %>
        <table class="w-full border-collapse shadow-md">
            <thead class="bg-gray-200">
            <tr>
                <th class="py-2 px-4 text-left">User ID</th>
                <th class="py-2 px-4 text-left">Username</th>
                <th class="py-2 px-4 text-left">Name</th>
                <th class="py-2 px-4 text-left">Role</th>
                <th class="py-2 px-4 text-left">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (User user : users) { %>
                <tr class="hover:bg-gray-50">
                    <td class="py-2 px-4"><%= user.getUserId() %></td>
                    <td class="py-2 px-4"><%= user.getUsername() %></td>
                    <td class="py-2 px-4"><%= user.getName() %></td>
                    <td class="py-2 px-4"><%= user.getRole() %></td>
                    <td class="py-2 px-4">
                        <!-- Actions like Edit and Delete -->
                        <a href="editUser.jsp?userId=<%= user.getUserId() %>" class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded">Edit</a>
                        <form action="DeleteUserController" method="POST" style="display:inline;">
                            <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                            <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Delete</button>
                        </form>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p class="text-center text-gray-500 mt-4">No users found.</p> 
    <% } %>

    <div class="mt-6 text-center"> 
        <a href="dashboard.jsp"><button class="button">Back to Dashboard</button></a>
    </div>

</div>

</body>
</html>
