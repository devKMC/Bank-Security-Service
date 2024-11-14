package com.devkmc.Bank_Security_Service.service;

import com.devkmc.Bank_Security_Service.entity.SecurityUser;
import com.devkmc.Bank_Security_Service.repository.SecurityUserRepository;
import com.devkmc.Bank_Security_Service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SecurityUserService {

    private final SecurityUserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final WebClient webClient;

    @Autowired
    public SecurityUserService(SecurityUserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, WebClient.Builder webClientBuilder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8003") // Bank_Customer_Service의 주소
                                         .build();
    }

    public SecurityUser registerUser(String username, String password) {
        // 비밀번호 암호화 및 JWT 토큰 생성
        String encodedPassword = passwordEncoder.encode(password);
        String jwtToken = jwtUtil.generateToken(username);
        SecurityUser newUser = new SecurityUser(username, encodedPassword, jwtToken);

        // 사용자 정보를 저장
        SecurityUser savedUser = userRepository.save(newUser);

        // Bank_Customer_Service에 사용자 정보 전달
        webClient.post()
                 .uri("/customer/register") // Bank_Customer_Service의 등록 엔드포인트
                 .bodyValue(savedUser)
                 .retrieve()
                 .bodyToMono(String.class)
                 .subscribe(response -> System.out.println("Response from Customer Service: " + response));

        return savedUser;
    }

    public String loginUser(String username, String password) {
        SecurityUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(username);
    }
}
