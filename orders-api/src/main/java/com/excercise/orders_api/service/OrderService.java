package com.excercise.orders_api.service;

import com.excercise.orders_api.dtos.OrderDTO;
import com.excercise.orders_api.dtos.PayloadDTO;
import com.excercise.orders_api.dtos.ProductsListDTO;
import com.excercise.orders_api.dtos.ResponseOrderDTO;
import com.excercise.orders_api.service.validations.services.CitySurchageCalculator;
import com.excercise.orders_api.service.validations.services.DiscountCalculator;
import com.excercise.orders_api.service.validations.services.ShippingCostCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private SubtotalCalculator subtotalCalculator;
    @Autowired
    private CitySurchageCalculator citySurchageCalculator;
    @Autowired
    private DiscountCalculator discuountCalculator;
    @Autowired
    private ShippingCostCalculator shippingCostCalculator;

    public ResponseOrderDTO manageOrder(PayloadDTO payloadData){
        OrderDTO order = payloadData.orderDTO();
        List<ProductsListDTO> products = payloadData.products();

        double totalWeightInLb = products.stream()
                .mapToDouble(product -> product.pesoEnLibraPorUnidad() * product.quantity())
                .sum();
        double shipingCost = shippingCostCalculator.calculateCBaseCost(totalWeightInLb);

        int quantityProductsOrder = products.stream()
                .mapToInt(product -> product.quantity())
                .sum();
        int stratumOrder = order.stratum();
        double citySurchageCost = citySurchageCalculator.calculateSurcharge(order.city(), quantityProductsOrder, stratumOrder);
        

    }

}
