package com.excercise.orders_api.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;


public record PayloadDTO(
        @NotEmpty(message = "Debes ingresar al menos un producto.")
        @UniqueElements(message = "No puedes ingresar más de una vez en el mismo producto.")
        List<@Valid ProductsListDTO> products,
        @Valid
        OrderDTO orderDTO
) {
}
