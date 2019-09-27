package com.czk.community.exception;

/**
 * created by srdczk 2019/9/27
 */
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(CustomizeErrorCode code) {
        message = code.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }

}
