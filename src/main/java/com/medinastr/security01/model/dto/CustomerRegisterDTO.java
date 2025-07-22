package com.medinastr.security01.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRegisterDTO {

    @NotNull
    @NotBlank
    private String email;

    private String password;

    @NotNull
    @NotBlank
    private String role;
}
