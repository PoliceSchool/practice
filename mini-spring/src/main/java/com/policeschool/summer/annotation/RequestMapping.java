package com.policeschool.summer.annotation;

import java.lang.annotation.*;

/**
 * @author ljx
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value();
}
