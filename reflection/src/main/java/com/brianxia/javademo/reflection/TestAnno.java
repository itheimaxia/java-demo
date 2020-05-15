package com.brianxia.javademo.reflection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Documented
@Inherited  //可以继承
public @interface TestAnno {
    String value() default "";
}
