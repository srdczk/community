package com.czk.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping(value = "/nima")
    public String nima(@RequestParam(name = "name", required = false, defaultValue = "NIMASILE") String name, Model model) {
        model.addAttribute("nima", name);
        return "nima";
    }
}
