package com.policeschool.summer.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    /**
     * 作用于该类上的注解有一个value属性，说白了就是controller的名称
     * @return
     */
    String value();
}
