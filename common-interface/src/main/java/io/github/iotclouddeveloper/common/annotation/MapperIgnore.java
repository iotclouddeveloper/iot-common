package io.github.iotclouddeveloper.common.annotation;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperIgnore {
}
