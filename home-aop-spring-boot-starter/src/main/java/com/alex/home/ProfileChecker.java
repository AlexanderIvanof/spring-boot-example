package com.alex.home;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * This class responsible for checking if application run with any profile.
 * If no profile specified {@link RuntimeException} will be thrown.
 *
 * @author Oleksandr Ivanov
 */
public class ProfileChecker implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (applicationContext.getEnvironment().getActiveProfiles().length == 0) {
            throw new RuntimeException("No profiles was specified...");
        }
    }
}
