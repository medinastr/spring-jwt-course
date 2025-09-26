package com.medinastr.security01.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class AuthenticationResponseDTO {

    private String jwtToken;
}
