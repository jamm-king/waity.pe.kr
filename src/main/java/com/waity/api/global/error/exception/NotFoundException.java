package com.waity.api.global.error.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private ErrorCode errorCode;

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
