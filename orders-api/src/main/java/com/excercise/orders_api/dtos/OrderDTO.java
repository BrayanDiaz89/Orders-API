package com.excercise.orders_api.dtos;

import com.excercise.orders_api.enums.CityEnum;
import jakarta.validation.constraints.*;

public record OrderDTO(
        @NotBlank(message = "Debes ingresar un id de la orden.")
        String idOrder,
        @NotNull(message = "Debes ingresar una ciudad válida de la lista.")
        CityEnum city,
        @NotBlank(message = "Debes ingresar el id del cliente.")
        String idClient,
        @NotNull(message = "Debe ingresar un estrato, no debe estar vacío este campo.")
        @Min(value = 1, message = "Debes ingresar un estrato válido, entre 1 y 6.")
        @Max(value = 6, message = "Debes ingresar un estrato válido, entre 1 y 6.")
        Integer stratum
) {
}
