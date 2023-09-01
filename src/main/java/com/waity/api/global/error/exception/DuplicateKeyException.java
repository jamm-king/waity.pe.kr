package com.waity.api.global.error.exception;

public class DuplicateKeyException extends RuntimeException {
    private ErrorCode errorCode;

    public DuplicateKeyException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
