package com.enu.taxes_service.domain.dto;

import com.enu.taxes_service.domain.TaxType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class TaxView {
    private Long id;
    private Double amount;
    private Long incomeId;
    private Long propertyId;
    private String description;
    private LocalDateTime date;
    private TaxType type;
}
