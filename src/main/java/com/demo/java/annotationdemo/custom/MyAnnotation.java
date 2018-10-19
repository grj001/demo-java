package com.demo.java.annotationdemo.custom;

public @interface MyAnnotation {
    String value1() default "2";

    MyEnum value2() default MyEnum.Sunny;
}

enum MyEnum {
    Sunny, Rainy
}
