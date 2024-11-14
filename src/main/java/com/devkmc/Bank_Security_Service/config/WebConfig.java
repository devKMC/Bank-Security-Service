package com.devkmc.Bank_Security_Service.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해 CORS 허용
                .allowedOrigins("http://localhost:5173")  // 클라이언트 URL 추가
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메소드 설정
                .allowedHeaders("*")  // 허용할 헤더 설정
                .allowCredentials(true);  // 쿠키와 자격 증명을 허용
    }
}
