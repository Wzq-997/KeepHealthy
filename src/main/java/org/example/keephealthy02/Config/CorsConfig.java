package org.example.keephealthy02.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOrigins("http://localhost:8082") // 允许来自这个源的请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // 允许的 HTTP 方法
                .allowedHeaders("*") // 允许的 HTTP 头部
                .allowCredentials(true); // 允许携带 credentials
    }
}