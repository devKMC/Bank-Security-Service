package com.devkmc.Bank_Security_Service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.devkmc.Bank_Security_Service.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String email); // 이메일로 사용자 찾기
    UserEntity findByUserId(String userId);
}
