/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 19-07-2025
 */
package com.barbatosdev.bdevbaasotp.controller;

import com.barbatosdev.bdevbaasotp.Enum.ErrorCode;
import com.barbatosdev.bdevbaasotp.dto.ValidateOtpRequest;
import com.barbatosdev.bdevbaasotp.dto.client.UserResponse;
import com.barbatosdev.bdevbaasotp.exception.CustomException;
import com.barbatosdev.bdevbaasotp.service.OtpService;
import com.barbatosdev.bdevbaasotp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author Esteban Lillo
 * @since 19-07-2025
 */
//@TODO: Add Service to handle OTP setup and validation
@RestController
@RequestMapping("/api/v1/otp")
@AllArgsConstructor
public class OtpController {

    private final UserService userService;
    private final OtpService otpService;

    @PostMapping("/setup")
    public ResponseEntity<?> setupOtp(@AuthenticationPrincipal Jwt jwt) {
        String currentUser = jwt.getSubject();
        UserResponse userResponse = userService.getUserById(UUID.fromString(currentUser));

        if (userResponse.isOtpEnabled()) {
            throw new CustomException(ErrorCode.OTP_ALREADY_ENABLED);
        }

        String secret = otpService.generateSecret();

        userService.enableOtp(UUID.fromString(currentUser), false, secret);

        String otpAuthUrl = otpService.generateOtpAuthUrl(userResponse.getUsername(), "BDEV-AUTH", secret);

        return ResponseEntity.ok(Map.of(
                "otpAuthUrl", otpAuthUrl,
                "message", "Escanea este código con Google Authenticator"
        ));
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmOtp(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody Map<String, String> body) {
        String currentUser = jwt.getSubject();
        UserResponse userResponse = userService.getUserById(UUID.fromString(currentUser));

        if (userResponse.isOtpEnabled()) {
            throw new CustomException(ErrorCode.OTP_ALREADY_ENABLED);
        }

        String otpCode = body.get("otpCode");
        if (otpCode == null) {
            return ResponseEntity.badRequest().body("Falta otpCode");
        }

        boolean valid = otpService.validateCode(userResponse.getOtpSecret(), otpCode);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OTP incorrecto");
        }

        userService.enableOtp(UUID.fromString(currentUser), true, userResponse.getOtpSecret());

        return ResponseEntity.ok("OTP activado correctamente");
    }


    @PostMapping("/validate")
    public ResponseEntity<?> confirmSignature(
            @RequestBody ValidateOtpRequest req) {
        UserResponse userResponse = userService.getUserById(UUID.fromString(req.getUserId()));

        if (!userResponse.isOtpEnabled()) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        if (req.getOtpCode() == null || req.getOtpCode().isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        boolean valid = otpService.validateCode(userResponse.getOtpSecret(), req.getOtpCode());
        if (!valid) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        return ResponseEntity.ok("OTP válido para autorizar la acción");
    }
}
