package com.f1.races_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EntityAlreadyExistsException extends RuntimeException {
    private String message;
}
