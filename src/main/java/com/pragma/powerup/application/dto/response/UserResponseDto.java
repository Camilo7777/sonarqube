package com.pragma.powerup.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String documentNumber;
    private String fistName;
    private String lastName;
    private String phone;
    private String email;
   private String password;
   private Long roleId;
}
