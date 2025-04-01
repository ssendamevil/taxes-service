package com.enu.taxes_service.controller;

import com.enu.taxes_service.domain.dto.UserCreate;
import com.enu.taxes_service.domain.dto.UserDto;
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
    //  Функция для создание пользователя
    @PostMapping("/register")
    public Long register(@RequestBody UserCreate user) {
        return userService.create(user).getId();
    }
    //  Функция для логина
    @PostMapping("/login")
    public UserDto login(@RequestBody UserLogin user) {
        return userService.login(user);
    }

}
