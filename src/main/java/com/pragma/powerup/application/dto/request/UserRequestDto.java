package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @Parameter(description = "Clave primaria autoincrementable")
    private Long id;
    private String documentNumber;
    private String fistName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Long roleId;
}
