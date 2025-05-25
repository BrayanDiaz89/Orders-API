package com.excercise.orders_api.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDTO(
        @NotNull(message = "Se debe suministrar el nombre del cliente")
        Integer orderNumber,
        @NotNull(message = "Se debe ingresar el estrato del cliente, que genera la orden")
        Integer socialStratum,
        @NotNull(message = "Debe ingresar una lista que contenga los productos de la orden")
        List<@NotNull ProductDTO> products
        ) {
}
