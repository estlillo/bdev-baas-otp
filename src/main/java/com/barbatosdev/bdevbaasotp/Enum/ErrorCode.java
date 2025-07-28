/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 04-07-2025
 */
package com.barbatosdev.bdevbaasotp.Enum;


import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Esteban Lillo
 * @since 04-07-2025
 */
@Getter
public enum ErrorCode {
    UNKNOWN_ERROR("GEN_100", "Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE("ERR_403", "Unavailable service", HttpStatus.SERVICE_UNAVAILABLE),
    INVALID_CREDENTIALS("AUTH_001", "Authentication failed", HttpStatus.UNAUTHORIZED),
    OTP_ALREADY_ENABLED("ERR_405", "OTP is already enabled for this user", HttpStatus.BAD_REQUEST),
    OTP_NOT_ENABLED("ERR_406", "OTP is not enabled for this user", HttpStatus.BAD_REQUEST),
    OTP_CODE_INVALID("ERR_407", "Invalid OTP code provided", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ACCESS("ERR_401", "Unauthorized access to the requested resource", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
