package com.czk.community.exception;

/**
 * created by srdczk 2019/9/27
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "您查找的问题不存在"),
    USER_NOT_SIGN_IN(2002, "用户未登录"),
    VIEW_COUNT_UPDATE_ERROR(2003, "浏览数更新失败,请重试"),
    TARGET_NOT_FOUND(2004, "未选择任何问题进行回复"),
    SYSTEM_ERROR(2005, "访问出错，要不您过会儿再试试"),
    TYPE_ERROR(2006, "您的问题种类有误"),
    COMMENT_NOT_FOUND(2007, "评论不存在"),
    REPLY_NOT_FOUND(2008, "该回复不存在"),
    URL_ERROR(2009, "您输入的URL不存在")
    ;
    @Override
    public String getMessage() {
        return message;
    }
    private Integer code;
    private String message;
    @Override
    public Integer getCode() {
        return code;
    }
    CustomizeErrorCode(Integer c, String m) {
        this.code = c;
        this.message = m;
    }
}
