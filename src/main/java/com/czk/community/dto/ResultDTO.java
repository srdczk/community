package com.czk.community.dto;

import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;

/**
 * created by srdczk 2019/9/28
 */
public class ResultDTO {
    private Integer code;
    private String message;

    public ResultDTO(CustomizeErrorCode errorCode) {
        code = errorCode.getCode();
        message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultDTO() {}

    public ResultDTO(CustomizeException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
