package com.enu.taxes_service.controller;

import com.enu.taxes_service.domain.dto.UserCreate;
import com.enu.taxes_service.domain.dto.UserLogin;
import com.enu.taxes_service.domain.model.User;
import com.enu.taxes_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public Long register(@RequestBody UserCreate user) {
        return userService.create(user).getId();
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLogin user) {
        return userService.login(user);
    }

}
