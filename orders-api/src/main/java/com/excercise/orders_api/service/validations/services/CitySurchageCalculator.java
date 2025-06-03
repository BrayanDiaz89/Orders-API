package com.excercise.orders_api.service.validations.services;

import com.excercise.orders_api.enums.CityEnum;
import org.springframework.stereotype.Service;

@Service
public class CitySurchageCalculator {

    public double calculateSurcharge(CityEnum city, int productsQuantity, int stratum, double subtotal){
        if(subtotal > 250000 || stratum == 1 || stratum == 2) return 0;
        int surchageCalculator = switch (city){
            case IBAGUE, BOGOTA -> 4000;
            case CALI, MEDELLIN -> 5000;
            case ARMENIA, PEREIRA, MANIZALES, BUCARAMANGA -> 6000;
            case BARRANQUILLA -> 7000;
            default -> 0;
        };
        return surchageCalculator * productsQuantity;
    }

}
