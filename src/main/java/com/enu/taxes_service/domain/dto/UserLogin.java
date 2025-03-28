package com.enu.taxes_service.domain.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLogin {
    private String username;
    private String password;
}
