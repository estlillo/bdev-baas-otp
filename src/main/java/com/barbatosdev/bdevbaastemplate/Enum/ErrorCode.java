/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 04-07-2025
 */
package com.barbatosdev.bdevbaastemplate.Enum;


import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Esteban Lillo
 * @since 04-07-2025
 */
@Getter
public enum ErrorCode {
    UNKNOWN_ERROR("GEN_100", "Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
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
