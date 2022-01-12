package com.test.manytomany.exception;

public class MoveException extends Exception{

    private String message;

    public MoveException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
