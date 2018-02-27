package com.alex.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Notification stuff. After context will be built up it mast refresh itself.
 * This is the point where will be possible to watch for some bean and make any notification.
 *
 * @author Oleksandr Ivanov
 */
@Slf4j
public class CustomListener implements ApplicationListener<ContextRefreshedEvent> {

    private final CustomProperties initialProperties;

    @Autowired
    public CustomListener(CustomProperties initialProperties) {
        this.initialProperties = initialProperties;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Context refreshed -> {}", event.getTimestamp());
        log.info("Got property from context: {}", initialProperties.getDestination());
    }
}
