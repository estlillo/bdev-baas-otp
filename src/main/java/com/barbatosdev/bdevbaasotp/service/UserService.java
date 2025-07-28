/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 19-07-2025
 */
package com.barbatosdev.bdevbaasotp.service;


import com.barbatosdev.bdevbaasotp.dto.client.UserResponse;
import com.barbatosdev.bdevbaasotp.exception.CustomException;

import java.util.UUID;

/**
 * @author Esteban Lillo
 * @since 19-07-2025
 */
public interface UserService {

    /**
     * Retrieves a user by their ID using the UserClient.
     *
     * @param userId the ID of the user to retrieve
     * @return UserResponse containing user details
     * @throws CustomException if the user service is unavailable
     */
    UserResponse getUserById(UUID userId);

    /**
     * Enables or disables OTP for a user.
     *
     * @param userId the ID of the user
     * @param enable true to enable OTP, false to disable
     * @param otpSecret the OTP secret code
     * @throws CustomException if the user service is unavailable
     */
    void enableOtp(UUID userId, boolean enable, String otpSecret);
}
