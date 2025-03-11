<%-- 
    Document   : receipt
    Created on : Feb 26, 2025, 11:49:57 PM
    Author     : Aruna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Receipt</title>
    <link rel="stylesheet" href="assets/recipt.css">
    <script src="https://cdn.rawgit.com/davidshimjs/qrcodejs/gh-pages/qrcode.min.js"></script>    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script>
      
        function generateQRCode() {
            const bookingId = "<%= request.getParameter("bookingId") %>";
            const customerPhone = "<%= request.getParameter("customerPhone") %>";
            const amount = "<%= request.getParameter("amount") %>";
            const paymentMethod = "<%= request.getParameter("paymentMethod") %>";      
            const qrData = `Booking ID: ${bookingId}\nPhone: ${customerPhone}\nAmount: ${amount} LKR\nMethod: ${paymentMethod}`;        
            const qrcode = new QRCode(document.getElementById("qrcode"), {
                text: qrData,
                width: 150,
                height: 150,
            });
        }

       
        function downloadPDF() {
            const { jsPDF } = window.jspdf;
            const doc = new jsPDF();        
            doc.setFontSize(18);
            doc.text("Receipt", 10, 20);
            doc.setFontSize(12);
            doc.text(`Booking ID: <%= request.getParameter("bookingId") %>`, 10, 30);
            doc.text(`Customer Phone: <%= request.getParameter("customerPhone") %>`, 10, 40);
            doc.text(`Amount Paid: <%= request.getParameter("amount") %> LKR`, 10, 50);
            doc.text(`Payment Method: <%= request.getParameter("paymentMethod") %>`, 10, 60);        
            const qrCodeImage = document.getElementById("qrcode").querySelector("img");
            if (qrCodeImage) {
                const qrCodeDataURL = qrCodeImage.src;
                doc.addImage(qrCodeDataURL, "PNG", 10, 70, 50, 50);
            }        
            doc.save("Receipt_<%= request.getParameter("bookingId") %>.pdf");
        }       
        window.onload = generateQRCode;
    </script>
</head>
<body>
    <div class="container">
        <h1>Receipt</h1>
        <p><strong>Booking ID:</strong> <%= request.getParameter("bookingId") %></p>
        <p><strong>Customer Phone:</strong> <%= request.getParameter("customerPhone") %></p>
        <p><strong>Amount Paid:</strong> <%= request.getParameter("amount") %> LKR</p>
        <p><strong>Payment Method:</strong> <%= request.getParameter("paymentMethod") %></p>
        <div id="qrcode"></div>
        <button onclick="downloadPDF()">Download as PDF</button>
        <div class="success-message">
            <strong>Please download your receipt to present as booking confirmation to the driver.</strong>
            <p>✅ Payment Successfully Completed!</p>
            <p>Thank you for choosing us. We look forward to serving you again!</p>
        </div>
    </div>
</body>
</html>