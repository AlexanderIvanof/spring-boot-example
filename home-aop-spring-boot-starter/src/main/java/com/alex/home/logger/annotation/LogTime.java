package com.alex.home.logger.annotation;

import java.lang.annotation.*;

/**
 * Method that will be marked with this annotation must log execution time
 *
 * @author Oleksandr Ivanov
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogTime {
}
