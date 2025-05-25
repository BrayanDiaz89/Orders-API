package com.excercise.orders_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank(message = "Debe ser suministrado el nombre del producto.")
        String name,
        @NotNull(message = "Debe ingresar un precio valido del producto.")
        Double price,
        @NotNull(message = "Debe ingresar una cantidad valida del producto.")
        Integer quantity

        ){
}
