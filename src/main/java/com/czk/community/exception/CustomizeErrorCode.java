package com.czk.community.exception;

/**
 * created by srdczk 2019/9/27
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("您查找的问题不存在"),
    USER_NOT_SIGN_IN("用户未登录");
    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String m) {
        message = m;
    }

}
