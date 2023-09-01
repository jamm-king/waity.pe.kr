package com.waity.api.global.error;

import com.waity.api.global.error.exception.ErrorCode;
import com.waity.api.global.error.exception.NotFoundException;
import com.waity.api.global.error.exception.customRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundException(NotFoundException ex){
        log.error("Entity Not Found Exception", ex);
        ErrorResponse responseBody = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(responseBody, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(customRuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeException(customRuntimeException ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponse responseBody = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        log.error("handleException", ex);
        ErrorResponse responseBody = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
