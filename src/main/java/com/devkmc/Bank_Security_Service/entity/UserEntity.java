package com.devkmc.Bank_Security_Service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login_user") // 
public class UserEntity {

    @Id
    private String userId;
    private String userName;
    private String email;
    private String userRole; // 사용자 역할 필드 추가

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() { // 역할 반환 메서드
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}