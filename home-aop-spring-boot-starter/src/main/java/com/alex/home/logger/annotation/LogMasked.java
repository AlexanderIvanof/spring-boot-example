package com.alex.home.logger.annotation;

import java.lang.annotation.*;

/**
 * Annotation for mark logging with some mask for business object.
 * Mask has two main symbols {@code #} - is show symbol from field,
 * <p>
 * and {@code *} - hide field symbol with <b>*</b> character. For more details see {@link com.alex.home.logger.util.LoggingUtil}.
 * <p>
 * Default: <i>do not hide any objects, but mask it with {@code ###########}.</i>
 * <p>
 * If length of field will be longer than 10 symbols it will be cut, otherwise length will be equal to field length.
 *
 * @author Oleksandr Ivanov
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMasked {
    boolean hide() default false;
    String mask() default "###########";
}
