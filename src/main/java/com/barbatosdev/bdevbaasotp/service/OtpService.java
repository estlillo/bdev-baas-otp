package com.barbatosdev.bdevbaasotp.service;

import com.barbatosdev.bdevbaasotp.dto.ValidateOtpRequest;

public interface OtpService {
    String getOtpUrl(String currentUserId);

    void confirmOtp(String currentUserId, String otpCode);

    boolean validateOtp(ValidateOtpRequest request);
}
