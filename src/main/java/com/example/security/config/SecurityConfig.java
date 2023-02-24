package com.example.security.config;

import com.example.security.exception.AccessDenyHandler;
import com.example.security.exception.AuthEntryPoint;
//import com.example.security.filter.AfterFilter;
import com.example.security.filter.AfterFilter;
import com.example.security.filter.BeforeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    private final BeforeFilter beforeFilter;
    private final AfterFilter afterFilter;

    public SecurityConfig(AuthEntryPoint authEntryPoint, AccessDenyHandler accessDenyHandler, BeforeFilter beforeFilter, AfterFilter afterFilter) {
        this.authEntryPoint = authEntryPoint;
        this.accessDenyHandler = accessDenyHandler;
        this.beforeFilter = beforeFilter;
        this.afterFilter = afterFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/")
                            .permitAll()
                        .requestMatchers(HttpMethod.POST, "/users")
                            .permitAll()
                        .requestMatchers(HttpMethod.GET, "/health")
                            .permitAll()
                        .anyRequest()
                            .authenticated()
                )
                .csrf().disable()
                .httpBasic(withDefaults())
                .addFilterBefore(beforeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(afterFilter, UsernamePasswordAuthenticationFilter.class)
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
