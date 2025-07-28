/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 04-07-2025
 */
package com.barbatosdev.bdevbaastemplate.exception;

import com.barbatosdev.bdevbaastemplate.Enum.ErrorCode;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Esteban Lillo
 * @since 04-07-2025
 */
@ResponseStatus
@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

  public CustomException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
