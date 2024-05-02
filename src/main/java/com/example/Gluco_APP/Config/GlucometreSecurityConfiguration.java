package com.example.Gluco_APP.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlucometreSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/glucose/**").permitAll();
                    auth.requestMatchers("/api/current/**").permitAll();
                    auth.requestMatchers("/api/glucose/current/**").permitAll();
                    // Autres configurations de sécurité...
                });

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Adapter selon les besoins réels de l'origine
                registry.addMapping("/api/**").allowedOrigins("http://192.168.2.21:8080/api/glucose/current?patientId=1");
            }
        };
    }
}