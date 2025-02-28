<%-- 
    Document   : customerPayment
    Created on : Feb 24, 2025, 10:45:52 PM
    Author     : Aruna
--%>

<html lang="en">
<head>
    <title>Make Payment</title>
    <link rel="stylesheet" href="assets/styles.css">
    <script src="assets/script.js"></script>
</head>
<body>
    <div class="container">
        <h2>Make a Payment</h2>
        <form action="PaymentController" method="POST">
            <label for="bookingId">Booking ID:</label>
            <input type="number" id="bookingId" name="bookingId" required>

            <label for="customerPhone">Phone Number:</label>
            <input type="text" id="customerPhone" name="customerPhone" required>

            <label for="amount">Amount:</label>
            <input type="text" id="amount" name="amount" required readonly>

            <label for="paymentMethod">Payment Method:</label>
            <select id="paymentMethod" name="paymentMethod">
                <option value="Credit Card">Credit Card</option>
                <option value="PayPal">PayPal</option>
            </select>

            <button type="submit">Pay Now</button>
        </form>
    </div>
</body>
</html>
