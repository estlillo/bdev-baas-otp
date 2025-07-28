/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 04-07-2025
 */
package com.barbatosdev.bdevbaastemplate.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author Esteban Lillo
 * @since 04-07-2025
 */
@Builder
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String code;
    private String message;
    private int status;
}
