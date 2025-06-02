package com.excercise.orders_api.infraestructure.dto;

import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

public record ErrorValidationDTO (
        String campo,
        String errorMessage
){
    public ErrorValidationDTO(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
