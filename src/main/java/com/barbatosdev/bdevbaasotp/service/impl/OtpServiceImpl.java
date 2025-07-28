package com.barbatosdev.bdevbaasotp.service.impl;

import com.barbatosdev.bdevbaasotp.Enum.ErrorCode;
import com.barbatosdev.bdevbaasotp.dto.ValidateOtpRequest;
import com.barbatosdev.bdevbaasotp.dto.client.UserResponse;
import com.barbatosdev.bdevbaasotp.exception.CustomException;
import com.barbatosdev.bdevbaasotp.service.OtpService;
import com.barbatosdev.bdevbaasotp.service.OtpServiceProvider;
import com.barbatosdev.bdevbaasotp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final UserService userService;
    private final OtpServiceProvider otpServiceProvider;

    @Override
    public String getOtpUrl(String currentUserId) {
        var userResponse = userService.getUserById(UUID.fromString(currentUserId));
        validateOtpNotEnabled(userResponse);

        var secret = otpServiceProvider.generateSecret();
        userService.enableOtp(UUID.fromString(currentUserId), false, secret);

        return otpServiceProvider.generateOtpAuthUrl(userResponse.getUsername(), "BDEV-AUTH", secret);
    }

    @Override
    public void confirmOtp(String currentUserId, String otpCode) {
        var userResponse = userService.getUserById(UUID.fromString(currentUserId));
        validateOtpNotEnabled(userResponse);
        validateOtpCode(otpCode);

        if (!otpServiceProvider.validateCode(userResponse.getOtpSecret(), otpCode)) {
            throw new CustomException(ErrorCode.OTP_CODE_INVALID);
        }

        userService.enableOtp(UUID.fromString(currentUserId), true, userResponse.getOtpSecret());
    }

    @Override
    public boolean validateOtp(ValidateOtpRequest request) {
        var userResponse = userService.getUserById(UUID.fromString(request.getUserId()));
        validateOtpEnabled(userResponse);
        validateOtpCode(request.getOtpCode());

        if (!otpServiceProvider.validateCode(userResponse.getOtpSecret(), request.getOtpCode())) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        return true;
    }

    private void validateOtpNotEnabled(UserResponse userResponse) {
        if (userResponse.isOtpEnabled()) {
            throw new CustomException(ErrorCode.OTP_ALREADY_ENABLED);
        }
    }

    private void validateOtpEnabled(UserResponse userResponse) {
        if (!userResponse.isOtpEnabled()) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    private void validateOtpCode(String otpCode) {
        if (otpCode == null || otpCode.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }
    }
}
