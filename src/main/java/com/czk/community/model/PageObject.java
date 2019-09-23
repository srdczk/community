package com.czk.community.model;

/**
 * created by srdczk 2019/9/23
 */
public class PageObject {

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
