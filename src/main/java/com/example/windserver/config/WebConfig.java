package com.example.windserver.config;

import com.example.windserver.common.JacksonObjectMapper;
import com.example.windserver.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) // 将 LoginInterceptor 实例添加到拦截器注册表
                .addPathPatterns("/**") // 指定拦截器应用于所有 URL
                .excludePathPatterns("/employee/login"); // 排除特定 URL 和资源路径
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建一个 MappingJackson2HttpMessageConverter，并将自定义的 ObjectMapper 设置为其使用的实例
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(new JacksonObjectMapper());
        // 将自定义的 messageConverter 添加到转换器列表中
        converters.add(0, messageConverter);
    }
}
