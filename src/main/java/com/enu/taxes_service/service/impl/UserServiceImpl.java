package com.enu.taxes_service.service.impl;

import com.enu.taxes_service.domain.dto.UserCreate;
import com.enu.taxes_service.domain.dto.UserLogin;
import com.enu.taxes_service.domain.model.User;
import com.enu.taxes_service.mapper.UserMapper;
import com.enu.taxes_service.repository.UserRepository;
import com.enu.taxes_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserCreate userCreate) {
        User userExists = userRepository.findByUsername(userCreate.getUsername());
        if (userExists != null) {
            throw new RuntimeException("Username already exists");
        }
        User user = UserMapper.INSTANCE.toEntity(userCreate);
        user.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User login(UserLogin userLogin) {
        User user = userRepository.findByUsername(userLogin.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return user;
    }
}
