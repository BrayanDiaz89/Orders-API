package com.excercise.orders_api.service.validations.services;

import org.springframework.stereotype.Service;

@Service
public class DiscountCalculator {

    public double getDiscountCalculator(double subtotal){
        if(subtotal >= 100000 && subtotal < 149000){
            return 0.1;
        } else if(subtotal <= 250000){
            return 0.2;
        } else if (subtotal > 250000){
            return 0.25;
        }
        return 0;
    }
    public boolean isHightValuePurchase(double subtotal){
        return subtotal > 250000;
    }

}

