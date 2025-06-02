package com.excercise.orders_api.service;

import com.excercise.orders_api.dtos.ProductsListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtotalCalculator {

    public double calculateSubtotal(List<ProductsListDTO> products){
        return products.stream()
                .mapToDouble(product -> product.precioUnidad() * product.quantity())
                .sum();
    }

}
