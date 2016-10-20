package com.chexun.base.framework.core.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.chexun.base.framework.core.mongod.CascadeType;

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.FIELD}) 
public @interface Cascade {
	CascadeType cascadeType() default CascadeType.NONE;
}
