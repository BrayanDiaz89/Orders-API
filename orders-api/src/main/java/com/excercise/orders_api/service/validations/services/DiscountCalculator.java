package com.excercise.orders_api.service.validations.services;

import org.springframework.stereotype.Service;

@Service
public class DiscountCalculator {

    public double getDiscountCalculator(double subtotal){
        if(subtotal >= 100000 && subtotal < 149999){
            return 0.1;
        } else if(subtotal >= 150000 && subtotal <= 250000){
            return 0.2;
        } else if (subtotal > 250000){
            return 0.25;
        } else{
            return 0;
        }
    }
}

