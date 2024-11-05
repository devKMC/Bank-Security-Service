package com.devkmc.Bank_Security_Service.service.implimentation;
import com.devkmc.Bank_Security_Service.entity.UserEntity;
import com.devkmc.Bank_Security_Service.repository.UserRepository;
import com.devkmc.Bank_Security_Service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository; // UserRepository 필드 선언

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity); // 사용자 생성
    }

    @Override
    public UserEntity getUserById(String userId) {
        return userRepository.findById(userId).orElse(null); // 사용자 조회
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll(); // 모든 사용자 조회
    }

    @Override
    public UserEntity updateUser(String userId, UserEntity userDetails) {
        UserEntity existingUser = getUserById(userId);
        if (existingUser != null) {
            existingUser.setUserName(userDetails.getUserName());
            existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser); // 사용자 정보 업데이트
        }
        return null; // 사용자가 존재하지 않으면 null 반환
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId); // 사용자 삭제
    }
}