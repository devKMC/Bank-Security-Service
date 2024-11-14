package com.devkmc.Bank_Security_Service.controller;

import com.devkmc.Bank_Security_Service.service.SecurityUserService;
import com.devkmc.Bank_Security_Service.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")  // 프론트엔드에서 오는 요청 허용
public class SecurityUserController {

    private final SecurityUserService userService;

    @Autowired
    public SecurityUserController(SecurityUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody SecurityUser user) {
        userService.registerUser(user.getUsername(), user.getPassword());
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }
}

