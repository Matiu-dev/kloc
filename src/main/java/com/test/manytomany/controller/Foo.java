package com.test.manytomany.controller;

import com.google.gson.*;

import java.lang.reflect.Type;

public class Foo<T> {

    private T type;

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
