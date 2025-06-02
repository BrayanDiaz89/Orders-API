package com.excercise.orders_api.service.validations.services;

import org.springframework.stereotype.Service;

@Service
public class ShippingCostCalculator {

    public double calculateCBaseCost(double totalWeightInLb){
        if(totalWeightInLb < 40) return 25000;
        if(totalWeightInLb <= 100) return 50000;
        return 100000;
    }

}
