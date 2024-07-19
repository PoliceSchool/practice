package com.policeschool.summer.annotation;

import java.lang.annotation.*;

/**
 * @author ljx
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
    String value();
}
