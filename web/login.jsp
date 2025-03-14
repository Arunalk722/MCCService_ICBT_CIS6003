<%-- 
    Document   : login
    Created on : Mar 12, 2025, 8:22:57 PM
    Author     : Aruna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Booking System - Login</title>
    <link rel="stylesheet" href="assets/Login.css">
</head>
<body>
    <div class="login-container">
        <h1>Login</h1>        
        <form action="LoginController" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <button type="submit" class="login-btn">Login</button>
        </form>
        <div class="login-footer">
            <p>Don't have an account? <a href="registration.html">Sign up</a></p>
        </div>
    </div>
</body>
</html>
