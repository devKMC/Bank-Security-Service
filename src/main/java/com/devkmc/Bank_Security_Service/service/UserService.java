package com.devkmc.Bank_Security_Service.service;

import java.util.List;
import com.devkmc.Bank_Security_Service.entity.UserEntity;


public interface UserService {
    UserEntity createUser(UserEntity userEntity);  // 사용자 생성
    UserEntity getUserById(String userId);         // 사용자 ID로 조회
    List<UserEntity> getAllUsers();                // 모든 사용자 조회
    UserEntity updateUser(String userId, UserEntity userDetails); // 사용자 정보 업데이트
    void deleteUser(String userId);                 // 사용자 삭제
}
