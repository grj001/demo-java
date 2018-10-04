package com.java.demo.annotationdemo.override;

public class testOverride {
    private String id;

    testOverride(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
