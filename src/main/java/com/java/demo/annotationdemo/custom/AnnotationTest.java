package com.java.demo.annotationdemo.custom;

public class AnnotationTest {

    @MyAnnotation(value1 = "1")
    public void execute(){
        System.out.println("method");
    }


}
