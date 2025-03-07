/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import dao.DiscountDAO;
import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author Aruna
 */
public class DiscountTesting {
    
    public DiscountTesting() {
    }
    
    @Test
     public void validDiscount() {
         //20 discount
        String discountCode="VIP20";
        DiscountDAO discountDAO = new DiscountDAO();
        float discountPercentage = discountDAO.getDiscountPercentage(discountCode);
        Assert.assertTrue("Found 20% discount",20.0==discountPercentage);
    }
      @Test
     public void invalidDiscount() {         
       String discountCode="NODis";
       DiscountDAO discountDAO = new DiscountDAO();
       float discountPercentage = discountDAO.getDiscountPercentage(discountCode);
        Assert.assertTrue("No Discount",0.0==discountPercentage);
    }
  
}
