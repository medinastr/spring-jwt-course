package com.medinastr.security01.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRegisterDTO {

    @NotNull(message = "error.dto.null.email")
    @NotBlank(message = "error.dto.null.email")
    private String email;

    private String password;

    @NotNull(message = "error.dto.null.role")
    @NotBlank(message = "error.dto.null.role")
    private String role;
}
