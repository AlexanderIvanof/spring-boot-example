package com.alex.home.logger.annotation;

import com.alex.home.logger.AspectLoggingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Switch on aspect logging for non Spring Boot project.
 *
 * @author Oleksandr Ivanov
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AspectLoggingConfiguration.class)
public @interface EnableAspectLogging {
}
