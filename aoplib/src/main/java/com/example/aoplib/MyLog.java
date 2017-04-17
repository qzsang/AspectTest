package com.example.aoplib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by little on 2017/4/17 0017.
 */

@Target({METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface MyLog {


}
