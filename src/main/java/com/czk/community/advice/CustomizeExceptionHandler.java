package com.czk.community.advice;

import com.alibaba.fastjson.JSON;
import com.czk.community.dto.ResultDTO;
import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * created by srdczk 2019/9/27
 */
@ControllerAdvice
public class CustomizeExceptionHandler  {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Throwable ex, Model model) {

        String contentType = request.getContentType();
        if (contentType.equals("application/json")) {
            //返回json
            ResultDTO resultDTO;
            if (ex instanceof CustomizeException) {
                resultDTO = new ResultDTO((CustomizeException) ex);
            } else {
                resultDTO = new ResultDTO(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setCharacterEncoding("utf-8");
                response.setStatus(200);
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
            }
            return null;
        } else {
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message",  "访问出错，要不您过会儿再试试");
            }
            return new ModelAndView("error");
        }
    }

}
