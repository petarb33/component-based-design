package com.f1.customer_service.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EntityDoesNotExistException extends RuntimeException {
    private String message;
}
