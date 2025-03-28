package com.enu.taxes_service.service;

import com.enu.taxes_service.domain.dto.UserCreate;
import com.enu.taxes_service.domain.dto.UserLogin;
import com.enu.taxes_service.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User create(UserCreate user);

    User login(UserLogin userLogin);
}
