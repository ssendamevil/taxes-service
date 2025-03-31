package com.enu.taxes_service.domain.dto;

import com.enu.taxes_service.domain.PropertyType;
import com.enu.taxes_service.domain.TaxType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyFilter {
    private PropertyType type;
    private LocalDateTime from;
    private LocalDateTime to;
    private Double min;
    private Double max;
}
