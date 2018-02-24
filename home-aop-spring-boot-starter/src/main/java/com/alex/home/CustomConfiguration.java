package com.alex.home;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sample configuration for notification. Will automatically apply to any {@code Spring Boot} project with
 * {@code spring.factories}.
 *
 * @author Oleksandr Ivanov
 */
@Configuration
public class CustomConfiguration {

    @Bean
    public CustomListener customListener() {
        return new CustomListener();
    }
}
