package com.lana.cc.backend.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/22 10:36
 */

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${lana.upload.image.filepath}")
    private String imageFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:"+imageFilePath);
    }
}
