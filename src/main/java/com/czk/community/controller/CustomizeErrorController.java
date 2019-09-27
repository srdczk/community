package com.czk.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * created by srdczk 2019/9/27
 */

@Controller
public class CustomizeErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
    @RequestMapping(value = ERROR_PATH, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request,
                                  Model model
                                  ) {
        HttpStatus status = getStatus(request);
        if (status.is4xxClientError()) {
            model.addAttribute("message", "您好请求出错了,请您换一个姿势吧！！");
        }
        if (status.is5xxServerError()) {
            model.addAttribute("message", "不好意思,我们的服务器出错了！！！");
        }
        return new ModelAndView("error");

    }
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
