package com.enu.taxes_service.domain.dto;

import com.enu.taxes_service.domain.IncomeType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class IncomeDto {
    private IncomeType type;
    private Double amount;
    private LocalDateTime date;
    private String source;
}
