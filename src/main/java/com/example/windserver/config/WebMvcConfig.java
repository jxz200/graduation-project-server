package com.example.windserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建一个自定义的 ObjectMapper（可根据你的需求进行配置）
        ObjectMapper objectMapper = new ObjectMapper();
        // 在这里，你可以根据需要自定义objectMapper的配置，例如：
        // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 创建一个 MappingJackson2HttpMessageConverter，并将自定义的 ObjectMapper 设置为其使用的实例
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(objectMapper);

        // 将自定义的 messageConverter 添加到转换器列表中
        converters.add(messageConverter);
    }
}