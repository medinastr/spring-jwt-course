package com.medinastr.security01.service.interfaces;

import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.dto.response.AuthenticationRequestDTO;

public interface AuthenticationService {

    void register(UserRegisterDTO registerDTO);

    String login(AuthenticationRequestDTO requestDTO);
}
