/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 19-07-2025
 */
package com.barbatosdev.bdevbaasotp.dto.client;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Esteban Lillo
 * @since 19-07-2025
 */
@Setter
@Getter
@Builder
public class EnableOtpRequest {
    private String userKey;
    private String otpSecret;
    private boolean enable;
}
