package com.medinastr.security01.service.interfaces;

import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.entity.User;

public interface UserService {

    // PERSIST

    User createNewUser(UserRegisterDTO registerDTO);

    // READ

    User getByEmail(String email);

    User getAuthenticated();

    // VALIDATIONS

    void validateConflictByEmail(String email);

    void validateDTO(UserRegisterDTO userRegisterDTO);
}
