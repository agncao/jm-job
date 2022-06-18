package com.jm.job.core.handle.annotation;

import java.lang.annotation.*;



@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JmJobHandle {
    String value() default "";
}
