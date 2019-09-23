package com.czk.community.model;

import java.util.HashMap;

/**
 * created by srdczk 2019/9/23
 */
public class ViewObject {

    private HashMap<String, Object> map = new HashMap<>();

    public void set(String s, Object o) {
        map.put(s, o);
    }

    public Object get(String s) {
        return map.get(s);
    }

}
