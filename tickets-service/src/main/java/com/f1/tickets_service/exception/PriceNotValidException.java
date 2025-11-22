package com.f1.tickets_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PriceNotValidException extends RuntimeException {
    private String message;
}
