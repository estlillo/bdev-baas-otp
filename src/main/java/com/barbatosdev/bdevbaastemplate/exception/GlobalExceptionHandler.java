/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 04-07-2025
 */
package com.barbatosdev.bdevbaastemplate.exception;

import com.barbatosdev.bdevbaastemplate.Enum.ErrorCode;
import com.barbatosdev.bdevbaastemplate.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author Esteban Lillo
 * @since 04-07-2025
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles CustomException and returns a structured error response.
     *
     * @param ex The CustomException to handle.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        return buildResponse(ex.getErrorCode(), ex.getErrorCode().getHttpStatus());
    }


    /**
     * Builds a structured error response based on the provided error code and HTTP status.
     *
     * @param code the error code to use for the response
     * @param status the HTTP status to use for the response
     * @return ResponseEntity containing the error response
     */
    private ResponseEntity<ErrorResponse> buildResponse(ErrorCode code, HttpStatus status) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(code.getCode())
                .message(code.getMessage())
                .status(status.value())
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
