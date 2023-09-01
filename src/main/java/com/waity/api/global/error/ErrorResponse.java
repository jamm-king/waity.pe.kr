package com.waity.api.global.error;

import com.waity.api.global.error.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }
    public ErrorResponse(ErrorCode errorCode, String message) {
        this.status = errorCode.getStatus();
        this.message = message;
        this.code = errorCode.getErrorCode();
    }
}
