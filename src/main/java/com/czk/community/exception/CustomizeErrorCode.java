package com.czk.community.exception;

/**
 * created by srdczk 2019/9/27
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("您查找的问题不存在"),
    USER_NOT_SIGN_IN("用户未登录"),
    VIEW_COUNT_UPDATE_ERROR("浏览数更新失败,请重试");
    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String m) {
        message = m;
    }

}
