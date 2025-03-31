package com.enu.taxes_service.service;

import com.enu.taxes_service.domain.dto.PropertyDto;
import com.enu.taxes_service.domain.dto.PropertyFilter;
import com.enu.taxes_service.domain.dto.PropertyView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PropertyService {
    PropertyView create(String username, PropertyDto dto);

    List<PropertyView> filterProperties(String username, PropertyFilter propertyFilter);
}
