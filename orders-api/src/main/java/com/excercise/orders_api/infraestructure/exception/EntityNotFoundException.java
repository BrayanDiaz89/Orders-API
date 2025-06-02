package com.excercise.orders_api.infraestructure.exception;

public class EntityNotFoundException extends ApplicationException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
