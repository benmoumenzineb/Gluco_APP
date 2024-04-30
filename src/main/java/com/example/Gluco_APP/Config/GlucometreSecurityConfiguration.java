package com.example.Gluco_APP.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class GlucometreSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    // Autorisation des points de terminaison spécifiques
                    auth.requestMatchers("/api/glucose/**").permitAll();
                    auth.requestMatchers("/api/current/**").permitAll();
                    // Autorisation du point de terminaison
                    // Autres configurations...
                });

        return http.build();
    }
}
