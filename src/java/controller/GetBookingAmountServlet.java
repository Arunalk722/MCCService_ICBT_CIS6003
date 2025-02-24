/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aruna
 */
@WebServlet(name = "GetBookingAmountServlet", urlPatterns = {"/GetBookingAmountServlet"})
public class GetBookingAmountServlet extends HttpServlet {

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
            out.println("<title>Servlet GetBookingAmountServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetBookingAmountServlet at " + request.getContextPath() + "</h1>");
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
        throws ServletException, IOException {    try {     
        int bookingIdParam = Integer.parseInt(request.getParameter("bookingId"));      
        System.out.println("Received bookingId: " + bookingIdParam);
        int bookingId = bookingIdParam;
        BookingDAO bookingDAO = new BookingDAO();
        float amount = bookingDAO.getBookingAmount(bookingId);
        if (amount == 0.0f) {
            response.setContentType("text/plain");
            response.getWriter().write("Error: Booking ID Not Found");
            return;
        }

        response.setContentType("text/plain");
        response.getWriter().write(String.format("%.2f", amount));

    } catch (NumberFormatException e) {
        System.err.println("Invalid Booking ID format: " + e.getMessage());
        response.setContentType("text/plain");
        response.getWriter().write("Error: Invalid Booking ID");
    } catch (Exception e) {
        System.err.println("Error retrieving booking amount: " + e.getMessage()); 
        response.setContentType("text/plain");
        response.getWriter().write("Error: Unable to retrieve amount");
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
        processRequest(request, response);
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
