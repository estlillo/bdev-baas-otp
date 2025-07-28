/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 19-07-2025
 */
package com.barbatosdev.bdevbaasotp.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @author Esteban Lillo
 * @since 19-07-2025
 */
@Getter
@Setter
public class ValidateOtpRequest {
    private String userId;
    private String otpCode;
}
