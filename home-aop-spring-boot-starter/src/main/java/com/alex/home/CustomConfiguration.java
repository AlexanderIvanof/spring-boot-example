package com.alex.home;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sample configuration for notification. Will automatically apply to any {@code Spring Boot} project with
 * {@code spring.factories}.
 *
 * @author Oleksandr Ivanov
 */
@Configuration
@EnableConfigurationProperties(CustomProperties.class)
public class CustomConfiguration {

    @Bean
    @ConditionalOnProperty("common.destination")
    public CustomListener customListener(CustomProperties initialProperties) {
        return new CustomListener(initialProperties);
    }
}
