package com.devkmc.Bank_Security_Service.config;

import com.devkmc.Bank_Security_Service.filter.JwtAuthenticationFilter;
import com.devkmc.Bank_Security_Service.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and()
            .csrf().disable()
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/auth/signup", "/api/auth/login").permitAll()  // 회원가입 및 로그인은 인증 없이 접근 가능
                .anyRequest().authenticated()  // 나머지 요청은 인증이 필요함
            )
            .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), 
                             org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
