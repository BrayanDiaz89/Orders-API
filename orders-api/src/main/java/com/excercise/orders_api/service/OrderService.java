package com.excercise.orders_api.service;

import com.excercise.orders_api.dtos.request.OrderDTO;
import com.excercise.orders_api.dtos.request.PayloadDTO;
import com.excercise.orders_api.dtos.request.ProductsListDTO;
import com.excercise.orders_api.dtos.response.ResponseOrderDTO;
import com.excercise.orders_api.enums.CityEnum;
import com.excercise.orders_api.service.validations.services.CitySurchageCalculator;
import com.excercise.orders_api.service.validations.services.DiscountCalculator;
import com.excercise.orders_api.service.validations.services.ShippingCostCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderService {

    private final double weightLbConvertKg = 0.45359237;
    @Autowired
    private CitySurchageCalculator citySurchageCalculator;
    @Autowired
    private DiscountCalculator discountCalculator;
    @Autowired
    private ShippingCostCalculator shippingCostCalculator;

    public ResponseOrderDTO manageOrder(PayloadDTO payloadData){
        OrderDTO order = payloadData.orderDTO();
        List<ProductsListDTO> products = payloadData.products();

        double totalWeightInLb = products.stream()
                .mapToDouble(product -> product.pesoEnLibraPorUnidad() * product.quantity())
                .sum();

        double shippingCost = shippingCostCalculator.calculateCBaseCost(totalWeightInLb);
        //Obtener cantidad de productos comprados, para el recargo por envío de cada uno, para la ciudad destino
        int quantityProductsOrder = products.stream()
                .mapToInt(ProductsListDTO::quantity)
                .sum();
        //Peso en Kilogramos del pedido completo
        double totalWeightInKg = products.stream()
                .mapToDouble(product -> (product.pesoEnLibraPorUnidad() * product.quantity()) * weightLbConvertKg)
                .sum();
        //Redondear resultado a 2 décimales.
        BigDecimal roundTotalWeightInKgUnd = new BigDecimal(totalWeightInKg).setScale(2, RoundingMode.HALF_UP);
        //Obtener atributos de order
        CityEnum city = order.city();
        int stratumOrder = order.stratum();
        String idOrder = order.idOrder();
        String idClient = order.idClient();
        //Valor total de los productos sin descuento.
        double subTotal = products.stream()
                .mapToDouble(p-> p.precioUnidad() * p.quantity())
                .sum();
        //Recargo de envío, por cada producto según ciudad y estrato
        double citySurchageCost = citySurchageCalculator.calculateSurcharge(city, quantityProductsOrder, stratumOrder, subTotal);
        //Variable que almacena el % a descontar, según criterio de precio total de productos, sin envío.
        double discountPercentege = discountCalculator.getDiscountCalculator(subTotal);
        //Variable booleana, para confirmar si el pedido tiene descuento o no.
        boolean existsDiscount = discountPercentege > 0;
        //Calcular valor de los productos con descuento
        double discountAmount = subTotal * discountPercentege;
        double totalValueWithDiscount = subTotal - discountAmount;
        //Calcular valor total del pedido con envíos, recargos y descuento.
        double totalAmountToPay = totalValueWithDiscount + citySurchageCost + shippingCost;

        return new ResponseOrderDTO(
                idOrder,
                city,
                idClient,
                subTotal,
                shippingCost,
                citySurchageCost,
                existsDiscount,
                discountPercentege,
                discountAmount,
                totalAmountToPay,
                roundTotalWeightInKgUnd
        );
    }
}
