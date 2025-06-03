package com.excercise.orders_api.dtos;

import com.excercise.orders_api.enums.CityEnum;

import java.math.BigDecimal;

public record ResponseOrderDTO(
    String idOrder,
    CityEnum ciudadDestino,
    String idClient,
    Double precioTotalSinDescuento,
    Boolean descuento,
    Double precioTotalAPagar,
    BigDecimal pesoDelPedidoEnKg
) {
}
