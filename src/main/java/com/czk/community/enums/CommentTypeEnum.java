package com.czk.community.enums;

/**
 * created by srdczk 2019/9/28
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    CommentTypeEnum(Integer t) {
        type = t;
    }

    public Integer getType() {
        return type;
    }

    public static boolean isExist(Integer i) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (i.equals(commentTypeEnum.getType())) return true;
        }
        return false;
    }

}
