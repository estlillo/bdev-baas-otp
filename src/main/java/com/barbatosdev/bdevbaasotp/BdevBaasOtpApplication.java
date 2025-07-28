package com.barbatosdev.bdevbaasotp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BdevBaasOtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdevBaasOtpApplication.class, args);
    }

}
