package com.excercise.orders_api.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductsListDTO(
        @NotBlank(message = "Debes ingresar el id del producto.")
        String idProduct,
        @NotBlank(message = "Debes ingresar el nombre del producto.")
        String nombre,
        @Positive(message = "El precio del producto debe ser un número positivo.")
        @NotNull(message = "Debes ingresar el precio del producto.")
        Double precioUnidad,
        @Min(value = 1, message = "La cantidad del producto debe ser almenos 1.")
        @NotNull(message = "Debes ingresar la cantidad de este producto.")
        Integer quantity,
        @Positive(message = "El peso del producto (en libras), debe ser un valor positivo.")
        @NotNull(message = "Debe ingresar un peso en libras del producto.")
        Double pesoEnLibraPorUnidad
) {
}
