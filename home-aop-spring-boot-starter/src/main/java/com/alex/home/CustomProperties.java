package com.alex.home;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Property that responsible for {@link CustomListener} initiation.
 *
 * @author Oleksandr Ivanov
 */
@Data
@ConfigurationProperties(prefix = "common")
public class CustomProperties {

    private String destination;

}
