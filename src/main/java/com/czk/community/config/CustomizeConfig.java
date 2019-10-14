package com.czk.community.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by srdczk 2019/10/14
 */
@Configuration
public class CustomizeConfig implements WebMvcConfigurer {
    @Value("${application.profile}")
    private String profile;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 图片传路径 */
        registry.addResourceHandler("/profile/**").addResourceLocations("file:" + profile);
    }
}
