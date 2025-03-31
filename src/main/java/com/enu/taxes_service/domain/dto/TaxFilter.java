package com.enu.taxes_service.domain.dto;

import com.enu.taxes_service.domain.IncomeType;
import com.enu.taxes_service.domain.TaxType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxFilter {
    private TaxType type;
    private LocalDateTime from;
    private LocalDateTime to;
    private Double min;
    private Double max;

}
