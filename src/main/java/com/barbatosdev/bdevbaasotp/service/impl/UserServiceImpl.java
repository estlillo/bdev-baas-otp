/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 19-07-2025
 */
package com.barbatosdev.bdevbaasotp.service.impl;


import com.barbatosdev.bdevbaasotp.Enum.ErrorCode;
import com.barbatosdev.bdevbaasotp.client.UserClient;
import com.barbatosdev.bdevbaasotp.dto.client.EnableOtpRequest;
import com.barbatosdev.bdevbaasotp.dto.client.UserResponse;
import com.barbatosdev.bdevbaasotp.exception.CustomException;
import com.barbatosdev.bdevbaasotp.service.UserService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Esteban Lillo
 * @since 19-07-2025
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserClient userClient;


    @Override
    public UserResponse getUserById(UUID userId) {
        try {
            return userClient.getUserById(userId).getBody();
        } catch (FeignException ex) {
            log.error("Error retrieving user otp by ID: {}", ex.getMessage());
            throw new CustomException(ErrorCode.SERVICE_UNAVAILABLE);
        }
    }

    /**
     * Enables or disables OTP for a user.
     *
     * @param userId the ID of the user
     * @param enable true to enable OTP, false to disable
     * @param otpSecret the OTP secret code
     * @throws CustomException if the user service is unavailable
     */
    @Override
    public void enableOtp(UUID userId, boolean enable, String otpSecret) {
        try {
            userClient.enableOtp(EnableOtpRequest.builder()
                    .userKey(String.valueOf(userId))
                    .otpSecret(otpSecret)
                    .enable(enable)
                    .build());
        } catch (FeignException ex) {
            log.error("Error enabling OTP for user ID {}: {}", userId, ex.getMessage());
            throw new CustomException(ErrorCode.SERVICE_UNAVAILABLE);
        }
    }
}
