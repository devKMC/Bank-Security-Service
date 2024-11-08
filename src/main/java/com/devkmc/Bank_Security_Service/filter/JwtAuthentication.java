package com.devkmc.Bank_Security_Service.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthentication extends UsernamePasswordAuthenticationToken {

    public JwtAuthentication(String username) {
        super(username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        // ROLE_USER는 예시로, 실제 사용자의 역할에 맞게 설정해야 합니다.
    }
}
