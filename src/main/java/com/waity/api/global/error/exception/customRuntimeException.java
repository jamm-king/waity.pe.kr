package com.waity.api.global.error.exception;

import lombok.Getter;

@Getter
public class customRuntimeException extends RuntimeException {
    private ErrorCode errorCode;

    public customRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public customRuntimeException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
