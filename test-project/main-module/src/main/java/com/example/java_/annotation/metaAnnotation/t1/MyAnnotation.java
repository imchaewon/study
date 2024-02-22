package com.example.java_.annotation.metaAnnotation.t1;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface MyAnnotation{}

@MyAnnotation
class C1{
	@MyAnnotation
	int i;

	@MyAnnotation
	C1 c;
}