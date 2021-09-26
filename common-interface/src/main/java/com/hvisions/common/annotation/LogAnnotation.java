package com.hvisions.common.annotation;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
}
