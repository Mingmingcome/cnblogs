package com.mingmingcome.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * @author: luhaoming
 * @date: 2018年8月16日 下午7:54:40
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	
}
