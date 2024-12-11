package org.hae.sfaas;

import org.hae.sfaas.global.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SfaasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfaasApplication.class, args);
    }

}
