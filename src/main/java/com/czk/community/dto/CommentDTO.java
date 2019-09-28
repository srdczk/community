package com.czk.community.dto;

/**
 * created by srdczk 2019/9/28
 */
public class CommentDTO {
    private Long parentId;
    private String text;
    private Integer type;


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
