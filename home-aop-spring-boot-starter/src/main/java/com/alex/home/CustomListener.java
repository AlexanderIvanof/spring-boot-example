package com.alex.home;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Notification stuff. After context will be built up it mast refresh itself.
 * This is the point where will be possible to watch for some bean and make any notification.
 *
 * @author Oleksandr Ivanov
 */
public class CustomListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Context refreshed ->" + event.getTimestamp());
    }
}
