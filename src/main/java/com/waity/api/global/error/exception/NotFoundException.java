package com.waity.api.global.error.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public NotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
