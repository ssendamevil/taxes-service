package com.enu.taxes_service.mapper;

import com.enu.taxes_service.domain.dto.PropertyDto;
import com.enu.taxes_service.domain.dto.PropertyView;
import com.enu.taxes_service.domain.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    Property toEntity(PropertyDto dto);

    PropertyDto toDto(Property property);

    PropertyView toView(Property property);
}
