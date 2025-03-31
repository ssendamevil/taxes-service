package com.enu.taxes_service.domain.dto;

import com.enu.taxes_service.domain.model.Income;
import com.enu.taxes_service.domain.model.Property;
import com.enu.taxes_service.domain.model.Tax;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserDto{
    private String username;
    private String firstname;
    private String lastname;
    private String middlename;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Income> incomes;
    private List<Tax> taxes;
    private List<Property> properties;
}
