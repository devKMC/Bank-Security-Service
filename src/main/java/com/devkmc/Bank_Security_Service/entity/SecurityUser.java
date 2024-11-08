package com.devkmc.Bank_Security_Service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SecurityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 자동 생성되는 ID (회원가입 시, 자동으로 생성)

    @Column(unique = true, nullable = false)
    private String username; // 고유 사용자 이름

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = true)
    private String jwtToken; // JWT 토큰을 저장할 필드

    // 기본 생성자
    public SecurityUser() {}

    // 파라미터를 받는 생성자
    public SecurityUser(String username, String password, String jwtToken) {
        this.username = username;
        this.password = password;
        this.jwtToken = jwtToken;
    }

    // Getter와 Setter 메서드들
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
