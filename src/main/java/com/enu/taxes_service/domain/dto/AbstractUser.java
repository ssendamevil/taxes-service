package com.enu.taxes_service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUser {
    private String username;
    private String firstname;
    private String lastname;
    private String middlename;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
