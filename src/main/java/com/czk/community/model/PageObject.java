package com.czk.community.model;

/**
 * created by srdczk 2019/9/23
 */
public class PageObject {
    //页面模型，包括分页显示的字(<<, <, 数字, >, >>) 和页面索引，给模板传递这个的List再遍历,完成分页
    private String id;
    private Integer val;

    public PageObject(String id, Integer val) {
        this.id = id;
        this.val = val;
    }

    public String getId() {
        return id;
    }

    public Integer getVal() {
         return val;
    }
}
