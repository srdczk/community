package com.czk.community.controller;

import com.czk.community.dto.FileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * created by srdczk 2019/10/13
 */
@Controller
public class FileController {

    @Value("${application.profile}")
    private String profile;

    @RequestMapping(value = "/file/upload")
    @ResponseBody
    public FileDTO upload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        String trueName = file.getOriginalFilename();
        String newName = System.currentTimeMillis() + "_" + (int)(Math.random() * 100) + trueName.substring(trueName.lastIndexOf('.'));
        System.out.println(newName);
        String path = profile;
        File tFile = new File(path, newName);

        try {
            file.transferTo(tFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setUrl("/profile/" + newName);
        fileDTO.setSuccess(1);
        return fileDTO;
    }
}
