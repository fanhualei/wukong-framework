package com.wukong.core.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface DatasourceAnno {
    String name() default DatasourceAnno.master;
    public static String master="master";
    public static String slave="slave";



}
