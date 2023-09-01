package com.waity.api.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ENTITY_NOT_FOUND(400,"C400","Entity is not found."),
    INVALID_OPERATION(400, "C401", "Operation is not supported."),
    DUPLICATE_KEY(400, "C402", "Key is duplicated."),
    INTER_SERVER_ERROR(500,"C500","Internal server error occured."),
    ;

    private int status;
    private String errorCode;
    private String message;
}
