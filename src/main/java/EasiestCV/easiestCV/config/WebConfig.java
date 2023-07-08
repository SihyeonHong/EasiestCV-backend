package EasiestCV.easiestCV.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Frontend URL
                .allowedMethods("*") // 필요한 HTTP 메소드를 허용하세요 (GET, POST, PUT, DELETE 등)
                .allowedHeaders("*") // 필요한 헤더를 허용하세요
                .allowCredentials(true);
    }
}

