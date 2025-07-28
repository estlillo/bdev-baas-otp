/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 11-07-2025
 */
package com.barbatosdev.bdevbaasotp.dto.client;

import lombok.*;

/**
 * @author Esteban Lillo
 * @since 11-07-2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String otpSecret;
    private boolean isOtpEnabled;
}
