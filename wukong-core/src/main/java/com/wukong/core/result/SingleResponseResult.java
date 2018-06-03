package com.wukong.core.result;


import java.lang.annotation.*;

/**
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SingleResponseResult {
    String value() default "result";

}
