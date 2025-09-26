package com.medinastr.security01.model.dto.response;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    private String email;

    private String password;
}
