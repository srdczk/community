package com.czk.community.util;

import com.czk.community.mapper.UserMapper;
import com.czk.community.model.PageObject;
import com.czk.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * created by srdczk 2019/9/26
 */
public class Util {


    public static User getUserByCookies(HttpServletRequest request, UserMapper userMapper) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    user = userMapper.getByToken(cookie.getValue());
                    break;
                }
            }
        }
        request.getSession().setAttribute("user", user);
        return user;
    }


    public static List<PageObject> getPageObject(int sum, int p) {

        int max = sum % 10 == 0 ? sum / 10 : sum / 10 + 1;

        if (p < 1) p = 1;
        if (p > max) p = max;
        List<PageObject> pos = new ArrayList<>();
        if (p > 3) pos.add(new PageObject("<<", 1));
        if (p > 1) pos.add(new PageObject("<", p - 1));
        for (int i = Math.max(1, p - 2); i <= p; i++) {
            pos.add(new PageObject(String.valueOf(i), i));
        }
        for (int i = p + 1; i <= Math.min(p + 2, max); i++) {
            pos.add(new PageObject(String.valueOf(i), i));
        }
        if (p < max) pos.add(new PageObject(">", p + 1));
        if (p < max - 2) pos.add(new PageObject(">>", max));
        return pos;
    }

}
