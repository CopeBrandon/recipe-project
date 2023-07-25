package com.launchcode.recipeproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadImagesConfiguration implements WebMvcConfigurer {

    @Override // add /uploads/ to resources so new images are loaded dynamically
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:///" + System.getProperty("user.dir") + "/src/main/uploads/");
    }
}
