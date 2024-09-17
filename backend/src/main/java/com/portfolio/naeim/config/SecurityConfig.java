package com.portfolio.naeim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/api/**").permitAll() // Allow unrestricted access to API endpoints
                                .anyRequest().authenticated() // Require authentication for all other requests
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurer() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // Specify the frontend origin
        configuration.addAllowedMethod("*"); // Allow all HTTP methods
        configuration.addAllowedHeader("*"); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all endpoints

        return source;
    }
}