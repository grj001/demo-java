package com.demo.java.annotationdemo.custom;

public class AnnotationTest {

    @MyAnnotation(value1 = "1")
    public void execute(){
        System.out.println("method");
    }


}
