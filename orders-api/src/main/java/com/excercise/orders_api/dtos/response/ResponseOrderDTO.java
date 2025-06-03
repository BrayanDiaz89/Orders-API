package com.excercise.orders_api.dtos.response;

import com.excercise.orders_api.enums.CityEnum;

import java.math.BigDecimal;

public record ResponseOrderDTO(
    String idOrder,
    CityEnum ciudadDestino,
    String idClient,
    Double precioTotalSinDescuento,
    Double precioDeEnvio,
    Double precioDeRecargoProductoPorEnvio,
    Boolean descuento,
    Double porcentajeDescontado,
    Double valorTotalADescontar,
    Double precioTotalAPagar,
    BigDecimal pesoDelPedidoEnKg
) {
}
