package com.enu.taxes_service.mapper;

import com.enu.taxes_service.domain.dto.UserCreate;
import com.enu.taxes_service.domain.dto.UserDto;
import com.enu.taxes_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserCreate userCreate);

    UserDto toDto(User user);
}
