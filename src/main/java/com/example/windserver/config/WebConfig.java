package com.example.windserver.config;

import com.example.windserver.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) // 将 LoginInterceptor 实例添加到拦截器注册表
                .addPathPatterns("/**") // 指定拦截器应用于所有 URL
                .excludePathPatterns("/employee/login"); // 排除特定 URL 和资源路径
    }
}
