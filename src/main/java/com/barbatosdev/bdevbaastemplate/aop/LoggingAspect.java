/*
 * Copyright (c) 2025 barbatos-dev
 * All rights reserved.
 *
 * Author: Esteban Lillo <barbatosdev@gmail.com>
 * Created on 04-07-2025
 */
package com.barbatosdev.bdevbaastemplate.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Esteban Lillo
 * @since 04-07-2025
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerMethods() {}

    @Around("restControllerMethods()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        log.info("Entering: {}", method);
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            log.error("Exception: - {} -> {}", method, ex.getMessage());
            throw ex;
        } finally {
            log.info("Exiting: {} ({} ms)", method, System.currentTimeMillis() - start);
        }
    }
}