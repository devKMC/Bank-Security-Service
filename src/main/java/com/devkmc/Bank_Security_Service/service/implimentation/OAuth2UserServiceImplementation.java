package com.devkmc.Bank_Security_Service.service.implimentation;

import com.devkmc.Bank_Security_Service.entity.UserEntity;
import com.devkmc.Bank_Security_Service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OAuth2UserServiceImplementation implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Autowired
    public OAuth2UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        
        // OAuth2User에서 필요한 정보 추출 (예: 이메일, 이름 등)
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email"); // 필요한 필드로 변경 가능
        String name = (String) attributes.get("name");

        // 사용자 엔티티 생성 또는 업데이트
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUserName(name);
            userEntity.setEmail(email);
            // 필요한 다른 필드 설정
        } else {
            userEntity.setUserName(name);
            // 필요한 다른 필드 업데이트
        }

        userRepository.save(userEntity); // 사용자 저장

        return oAuth2User; // OAuth2User 반환
    }
}
