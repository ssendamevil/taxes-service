package com.enu.taxes_service.controller;

import com.enu.taxes_service.domain.PropertyType;
import com.enu.taxes_service.domain.dto.PropertyDto;
import com.enu.taxes_service.domain.dto.PropertyFilter;
import com.enu.taxes_service.domain.dto.PropertyView;
import com.enu.taxes_service.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyService propertyService;
    @PostMapping("/{username}")
    public PropertyView createProperty(@RequestBody PropertyDto propertyDto,
                                       @PathVariable String username){
        return propertyService.create(username, propertyDto);
    }

    @GetMapping("/{username}")
    public List<PropertyView> getAllProperties(@PathVariable String username,
                                               @RequestParam(required = false)PropertyType type,
                                               @RequestParam(required = false)LocalDateTime from,
                                               @RequestParam(required = false)LocalDateTime to,
                                               @RequestParam(required = false)Double min,
                                               @RequestParam(required = false)Double max){
        PropertyFilter propertyFilter = PropertyFilter.builder()
                .from(from)
                .type(type)
                .to(to)
                .max(max)
                .min(min)
                .build();
        return propertyService.filterProperties(username, propertyFilter);
    }
}
