/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import dao.PaymentDAO;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author Aruna
 */
public class VeriflyBooking {
    
    public VeriflyBooking() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void trueMobileWithBookingId() {         
       int bookingId = 3;
        String customerPhone = "0776461602";
        PaymentDAO paymentDAO = new PaymentDAO();        
        assertEquals(true, paymentDAO.validateBookingBeforePayment(bookingId, customerPhone));        
     }
      @Test
      public void falseMobileWithBookingId() {         
       int bookingId = 3;
        String customerPhone = "0701779445";
        PaymentDAO paymentDAO = new PaymentDAO();
        assertEquals(false, paymentDAO.validateBookingBeforePayment(bookingId, customerPhone)); 
     }
}
