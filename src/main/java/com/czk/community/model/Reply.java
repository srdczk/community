package com.czk.community.model;

/**
 * created by srdczk 2019/10/12
 */
public class Reply {

    //回复Id
    private Integer id;

    //问题的id//建立链接去问题
    private Integer questionId;
    //回复的方式
    private Integer type;
    //回复人昵称
    private String creator;
    //回复了谁
    private Integer owner;
    private Long gmtCreate;
    private String showString;

    private Integer vis;

    public String getShowString() {
        return showString;
    }

    public void setShowString(String showString) {
        this.showString = showString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getVis() {
        return vis;
    }

    public void setVis(Integer vis) {
        this.vis = vis;
    }
}
