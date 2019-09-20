package com.czk.community.controller;

import com.czk.community.dto.AccessTokenDTO;
import com.czk.community.dto.GithubUser;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.User;
import com.czk.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * create by srdczk  2019/9/17
 */
@Controller
public class AuthorizeController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GithubProvider provider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUrl;
    @GetMapping(value = "/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        String s = provider.getAccessToken(accessTokenDTO);
        String p = s.substring(s.indexOf('=') + 1, s.indexOf('&'));
        System.out.println(p);
        GithubUser githubUser = provider.getUser(p);
        System.out.println(githubUser);
        if (githubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatar(githubUser.getAvatar_url());
            user.setBio(githubUser.getBio());
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));
            //登陆成功
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
