package com.alex.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * This type is working on very early time before {@link org.springframework.context.ApplicationContext} stated.
 *
 * @author Oleksandr Ivanov
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProfileInitiator implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("All environment properties:");
        environment.getSystemProperties().forEach((key, value) -> log.info("Property: -> {}:{}", key, value));
    }
}
