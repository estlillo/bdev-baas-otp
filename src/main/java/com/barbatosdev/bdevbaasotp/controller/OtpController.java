/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 19-07-2025
 */
package com.barbatosdev.bdevbaasotp.controller;

import com.barbatosdev.bdevbaasotp.dto.ValidateOtpRequest;
import com.barbatosdev.bdevbaasotp.service.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Esteban Lillo
 * @since 19-07-2025
 */
@RestController
@RequestMapping("/api/v1/otp")
@AllArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/setup")
    public ResponseEntity<Map<String, String>> setupOtp(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(Map.of(
                "otpAuthUrl", otpService.getOtpUrl(jwt.getSubject()),
                "message", "Escanea este código con Google Authenticator"
        ));
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmOtp(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody Map<String, String> body) {
        otpService.confirmOtp(jwt.getSubject(), body.get("otp"));
        return ResponseEntity.ok("OTP activado correctamente");
    }

    @PostMapping("/validate")
    public ResponseEntity<String> confirmSignature(@RequestBody ValidateOtpRequest req) {
        otpService.validateOtp(req);
        return ResponseEntity.ok("OTP válido para autorizar la acción");
    }
}
