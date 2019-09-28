package com.czk.community.model;

/**
 * created by srdczk 2019/9/28
 */
/**
 * CREATE TABLE comment
 (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 parent_id BIGINT,
 type INT NOT NULL,
 creator INT NOT NULL,
 gmt_create BIGINT,
 gmt_modified BIGINT,
 like_count INT DEFAULT 0
 );
 * */
public class Comment {
    private Long id;
    private Long parentId;
    private Integer type;
    private Integer creator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
