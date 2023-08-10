package com.waity.api.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ENTITY_NOT_FOUND(400,"C400","ENTITY NOT FOUND"),
    INTER_SERVER_ERROR(500,"C500","INTER SERVER ERROR"),
    ;

    private int status;
    private String errorCode;
    private String message;
}
