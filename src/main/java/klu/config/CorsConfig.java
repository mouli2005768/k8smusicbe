package klu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // ✅ Allow all API endpoints
                registry.addMapping("/api/**")
                        .allowedOrigins(
                                // Local development
                                "http://localhost:5173",
                                "http://localhost:3000",
                                "http://localhost:8082",

                                // Kubernetes frontend
                                "http://frontend:8082",
                                "http://frontend",
                                "http://localhost:30082",
                                "http://127.0.0.1:30082",

                                // For production NodePort access
                                "*"
                        )
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(false);
                // ✅ Allow static song files
                registry.addMapping("/songs/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET");

                // ✅ Allow static image files
                registry.addMapping("/images/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET");
            }
        };
    }
}
