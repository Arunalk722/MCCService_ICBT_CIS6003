/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Aruna
 */
public class PercentageDiscount implements DiscountStrategy {
    private float discountPercentage;

    public PercentageDiscount(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public float applyDiscount(float totalAmount) {
        return totalAmount - (totalAmount * (discountPercentage / 100));
    }
}
