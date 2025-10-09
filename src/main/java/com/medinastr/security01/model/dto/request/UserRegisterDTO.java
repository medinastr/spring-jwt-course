package com.medinastr.security01.model.dto.request;

import com.medinastr.security01.enums.AuthoritiesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterDTO {

  @NotNull(message = "error.dto.null.email")
  @NotBlank(message = "error.dto.null.email")
  private String email;

  private String password;

  @NotNull(message = "error.dto.null.role")
  private AuthoritiesEnum role;
}
