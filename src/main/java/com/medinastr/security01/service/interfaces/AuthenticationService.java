package com.medinastr.security01.service.interfaces;

import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.dto.response.AuthenticationRequestDTO;
import com.medinastr.security01.model.entity.User;

public interface AuthenticationService {

    User register(UserRegisterDTO registerDTO);

    String login(AuthenticationRequestDTO requestDTO);
}
