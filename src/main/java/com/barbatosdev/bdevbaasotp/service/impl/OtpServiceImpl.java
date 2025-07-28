/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 19-07-2025
 */
package com.barbatosdev.bdevbaasotp.service.impl;


import com.barbatosdev.bdevbaasotp.service.OtpService;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import org.springframework.stereotype.Service;

/**
 * @author Esteban Lillo
 * @since 19-07-2025
 */
@Service
public class OtpServiceImpl implements OtpService {

    private static final String ISSUER = "BDEV-AUTH";
    private final DefaultSecretGenerator secretGenerator = new DefaultSecretGenerator();
    private final CodeVerifier verifier = new DefaultCodeVerifier(new DefaultCodeGenerator(), new SystemTimeProvider());

    @Override
    public String generateSecret() {
        return secretGenerator.generate();
    }

    @Override
    public String generateOtpAuthUrl(String username, String issuer, String secret) {
        QrData data = new QrData.Builder()
                .label(username)
                .secret(secret)
                .issuer(issuer)
                .algorithm(HashingAlgorithm.valueOf("SHA1"))
                .digits(6)
                .period(30)
                .build();
        return data.getUri(); // otpauth://totp/...
    }

    @Override
    public boolean validateCode(String secret, String code) {
        return verifier.isValidCode(secret, code);
    }

}
