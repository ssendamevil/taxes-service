package com.enu.taxes_service.domain.dto;

import com.enu.taxes_service.domain.PropertyType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDto {
    private Double price;
    private String description;
    private LocalDateTime date;
    private PropertyType type;
}
