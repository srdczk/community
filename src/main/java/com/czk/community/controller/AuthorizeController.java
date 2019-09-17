package com.czk.community.controller;

import com.czk.community.dto.AccessTokenDTO;
import com.czk.community.dto.GithubUser;
import com.czk.community.provider.GithubProvider;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by srdczk  2019/9/17
 */
@Controller
public class AuthorizeController {

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
                           @RequestParam(name = "state") String state, Model model) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        String s = provider.getAccessToken(accessTokenDTO);
        String p = s.substring(s.indexOf('=') + 1, s.indexOf('&'));
        System.out.println(p);
        GithubUser user = provider.getUser(p);
        System.out.println(user);
        return "index";
    }
}
