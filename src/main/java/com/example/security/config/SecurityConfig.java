package com.example.security.config;

import com.example.security.exception.AccessDenyHandler;
import com.example.security.exception.AuthEntryPoint;
//import com.example.security.filter.AfterFilter;
import com.example.security.filter.AfterFilter;
import com.example.security.filter.BeforeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthEntryPoint authEntryPoint;
    private final AccessDenyHandler accessDenyHandler;

    public SecurityConfig(AuthEntryPoint authEntryPoint, AccessDenyHandler accessDenyHandler) {
        this.authEntryPoint = authEntryPoint;
        this.accessDenyHandler = accessDenyHandler;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.securityMatcher("/", "/health")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/")
                            .permitAll()
                        .requestMatchers(HttpMethod.GET, "/health")
                            .permitAll()
                )
                .csrf().disable()
                .httpBasic(withDefaults())
                .addFilterBefore(new BeforeFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint)
                    .accessDeniedHandler(accessDenyHandler).and()
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        return http.securityMatcher("/users/**")
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/users")
                    .permitAll()
                .requestMatchers("/users/**")
                    .authenticated().and()
                .csrf().disable()
                .addFilterAfter(new AfterFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint)
                    .accessDeniedHandler(accessDenyHandler).and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
