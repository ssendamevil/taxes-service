package com.enu.taxes_service.service.impl;

import com.enu.taxes_service.domain.dto.PropertyDto;
import com.enu.taxes_service.domain.dto.PropertyFilter;
import com.enu.taxes_service.domain.dto.PropertyView;
import com.enu.taxes_service.domain.model.Property;
import com.enu.taxes_service.domain.model.Tax;
import com.enu.taxes_service.domain.model.User;
import com.enu.taxes_service.mapper.PropertyMapper;
import com.enu.taxes_service.mapper.TaxMapper;
import com.enu.taxes_service.repository.PropertyRepository;
import com.enu.taxes_service.repository.UserRepository;
import com.enu.taxes_service.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    @Override
    public PropertyView create(String username, PropertyDto dto) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        Property property = PropertyMapper.INSTANCE.toEntity(dto);
        property.setUser(user);
        return PropertyMapper.INSTANCE.toView(propertyRepository.save(property));
    }

    @Override
    public List<PropertyView> filterProperties(String username, PropertyFilter propertyFilter) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        List<Property> properties = propertyRepository.findByUserId(user.getId());
        List<Property> filteredProperties = properties;
        if(Objects.nonNull(propertyFilter.getType())){
            filteredProperties = properties.stream().filter(tax -> Objects.equals(tax.getType(), propertyFilter.getType())).toList();
        }
        if((Objects.nonNull(propertyFilter.getTo()) && Objects.nonNull(propertyFilter.getFrom()))
                && propertyFilter.getFrom().isBefore(propertyFilter.getTo())){
            filteredProperties = filteredProperties.stream().filter(tax -> propertyFilter.getTo().isAfter(tax.getDate()) && propertyFilter.getFrom().isBefore(tax.getDate())).toList();
        }
        if((Objects.nonNull(propertyFilter.getMin()) && Objects.nonNull(propertyFilter.getMax()))
                && propertyFilter.getMax() > propertyFilter.getMin()){
            filteredProperties = filteredProperties.stream().filter(tax -> propertyFilter.getMax() >= tax.getPrice() && propertyFilter.getMin() <= tax.getPrice()).toList();
        }
        return filteredProperties.stream().map(PropertyMapper.INSTANCE::toView).toList();
    }
}
