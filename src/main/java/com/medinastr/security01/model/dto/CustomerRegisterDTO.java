package com.medinastr.security01.model.dto;

import lombok.Data;

@Data
public class CustomerRegisterDTO {

    private String email;

    private String password;

    private String role;
}
