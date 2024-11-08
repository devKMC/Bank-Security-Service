package com.devkmc.Bank_Security_Service.service;

import com.devkmc.Bank_Security_Service.entity.SecurityUser;
import com.devkmc.Bank_Security_Service.repository.SecurityUserRepository;
import com.devkmc.Bank_Security_Service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService {

    private final SecurityUserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityUserService(SecurityUserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public SecurityUser registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        String jwtToken = jwtUtil.generateToken(username);
        SecurityUser newUser = new SecurityUser(username, encodedPassword, jwtToken);
        return userRepository.save(newUser);
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
