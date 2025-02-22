/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookingDAO;
import dao.DiscountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Booking;
import model.DiscountStrategy;
import model.PercentageDiscount;

/**
 *
 * @author Aruna
 */
@WebServlet(name = "BookingController", urlPatterns = {"/BookingController"})
public class BookingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookingController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");
        if ("cancel".equals(action)) {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            BookingDAO bookingDAO = new BookingDAO();
            boolean canceled = bookingDAO.deleteBooking(bookingId);

            if (canceled) {
                response.sendRedirect("cancelBooking.jsp?status=success");
            } else {
                response.sendRedirect("cancelBooking.jsp?status=failed");
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerPhone = request.getParameter("customerPhone");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        float totalAmount = Float.parseFloat(request.getParameter("totalAmount"));
        String discountCode = request.getParameter("discountCode");

        // Get Discount Percentage
        DiscountDAO discountDAO = new DiscountDAO();
        float discountPercentage = discountDAO.getDiscountPercentage(discountCode);

        // Apply Discount
        DiscountStrategy discountStrategy = new PercentageDiscount(discountPercentage);
        float finalAmount = discountStrategy.applyDiscount(totalAmount);

        // Create Booking
        Booking booking = new Booking(source, destination, customerPhone, vehicleId, driverId, finalAmount);
        BookingDAO bookingDAO = new BookingDAO();
        int bookingId = bookingDAO.createBooking(booking);

        if (bookingId > 0) {
            response.sendRedirect("bookingSuccess.jsp?bookingId=" + bookingId + "&finalAmount=" + finalAmount);
        } else {
            response.sendRedirect("bookingError.jsp?error=Booking failed.");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
