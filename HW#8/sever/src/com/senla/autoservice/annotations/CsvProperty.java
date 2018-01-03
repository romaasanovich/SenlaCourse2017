package com.senla.autoservice.annotations;

public @interface CsvProperty {
	PropertyType propertyType();
	int columnNumber() default 0;
	String keyField() default "";
}
