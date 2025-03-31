package com.enu.taxes_service.domain.dto;

import com.enu.taxes_service.domain.PropertyType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PropertyView {
    private Long id;
    private Double price;
    private String description;
    private LocalDateTime date;
    private PropertyType type;

}
