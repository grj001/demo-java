package com.demo.java.lambda;

import java.util.function.DoubleUnaryOperator;

public class DoubleUaryOpeartorDemo{

    public static void main(String[] args){
        DoubleUnaryOperator d = x ->{
            return x*2;
        };
        System.out.println(d.applyAsDouble(1.0D));
    }

}
