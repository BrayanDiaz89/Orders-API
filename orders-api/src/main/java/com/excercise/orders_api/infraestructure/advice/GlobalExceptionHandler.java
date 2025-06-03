package com.excercise.orders_api.infraestructure.advice;

import com.excercise.orders_api.infraestructure.dto.ErrorValidationDTO;
import com.excercise.orders_api.infraestructure.exception.ApplicationException;
import com.excercise.orders_api.infraestructure.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity tratarError404() {
            return ResponseEntity.notFound().build();
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
            var errors = e.getFieldErrors().stream()
                    .map(ErrorValidationDTO::new).toList();
            return ResponseEntity.badRequest().body(errors);
        }

        @ExceptionHandler(ApplicationException.class)
        public ResponseEntity<String> tratarErrorDeValidacion(ApplicationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

}
