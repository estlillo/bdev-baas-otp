/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 11-07-2025
 */
package com.barbatosdev.bdevbaasotp.client;

import com.barbatosdev.bdevbaasotp.dto.client.EnableOtpRequest;
import com.barbatosdev.bdevbaasotp.dto.client.EnableOtpResponse;
import com.barbatosdev.bdevbaasotp.dto.client.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @author Esteban Lillo
 * @since 11-07-2025
 */
@FeignClient(name = "userClient", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/api/v1/users/find-by-id-otp")
    ResponseEntity<UserResponse> getUserById(@RequestParam UUID userId);

    @PostMapping("/api/v1/users/enable-otp")
    ResponseEntity<EnableOtpResponse> enableOtp(@RequestBody EnableOtpRequest request);
}
