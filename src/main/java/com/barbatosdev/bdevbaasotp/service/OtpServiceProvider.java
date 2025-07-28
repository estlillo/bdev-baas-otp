/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 19-07-2025
 */
package com.barbatosdev.bdevbaasotp.service;


/**
 * @author Esteban Lillo
 * @since 19-07-2025
 */
public interface OtpServiceProvider {
    String generateSecret();

    String generateOtpAuthUrl(String username, String issuer, String secret);

    boolean validateCode(String secret, String code);
}
