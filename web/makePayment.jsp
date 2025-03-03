<%-- 
    Document   : makePayment
    Created on : Feb 24, 2025, 7:45:47 PM
    Author     : Aruna
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bookingId = request.getParameter("bookingId");
    String customerPhone = request.getParameter("customerPhone");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="assets/MakePayment.css">
    <title>Make Payment</title>
    <script>
        function fetchAmount() {
            let bookingId = document.getElementById("bookingId").value;
            console.log(bookingId);
            let amountField = document.getElementById("amount");
            if (bookingId) {
                fetch(`GetBookingAmountController?bookingId=`+bookingId)
                    .then(response => response.text())
                    .then(amount => {
                        if (isNaN(amount) || amount === "Error") {
                            amountField.value = "Error: Invalid Booking ID";
                            amountField.style.color = "red";
                        } else {
                            amountField.value = amount;
                            amountField.style.color = "black";
                        }
                    })
                    .catch(error => {
                        console.error("Error fetching amount:", error);
                        amountField.value = "Error: Unable to retrieve amount";
                        amountField.style.color = "red";
                    });
            }
        }

        window.onload = fetchAmount; 
    </script>
</head>
<body>
    <div class="container">
        <h2>Complete Your Payment</h2>
        <form action="PaymentController" method="POST">
            <input type="hidden" name="bookingId" id="bookingId" value="<%= bookingId %>">
            <input type="hidden" name="customerPhone" value="<%= customerPhone %>">

            <label for="amount">Amount:</label>
            <input type="text" id="amount" name="amount" readonly required>

            <label for="paymentMethod">Payment Method:</label>
            <select name="paymentMethod">
                <option value="Credit Card">Credit Card</option>
                <option value="Credit Card">Bank Transfer</option>
                <option value="PayPal">PayPal</option>
            </select>

            <button type="submit">Pay Now</button>
        </form>
    </div>
</body>
</html>