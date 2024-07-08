package com.Hasindu.Online.Food.Ordering.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(managment ->managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS)    // Configure session management to be stateless
                ) .authorizeHttpRequests(                // Configure authorization rules for HTTP requests
                        Authorize -> Authorize
                                .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN")    // Only users with roles 'RESTAURANT_OWNER' or 'ADMIN' can access /api/admin/** endpoints
                                .requestMatchers("/api/**").authenticated()    // All /api/** endpoints require authentication
                                .anyRequest().permitAll()    // Permit all other requests without authentication
                ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)   // Add a custom JWT token validator filter before the BasicAuthenticationFilter
                .csrf(csrf -> csrf.disable())      // Disable CSRF protection (not recommended for stateful sessions, but okay for stateless)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));     // Configure CORS settings using a custom configuration source

        return null; // Return null here might be a placeholder or an oversight
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Arrays.asList(
                        "http://localhost:5432",
                        "**"
                ));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setExposedHeaders(Collections.singletonList("*"));
                cfg.setExposedHeaders(Arrays.asList("authorization"));
                cfg.setMaxAge(3600L);
                return cfg;
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

