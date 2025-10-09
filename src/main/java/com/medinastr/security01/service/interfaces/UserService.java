package com.medinastr.security01.service.interfaces;

import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.entity.Account;
import com.medinastr.security01.model.entity.User;

public interface UserService {

  // PERSIST

  User createNewUser(UserRegisterDTO registerDTO);

  User saveLastLoginAccount(User user, Account account);

  // READ

  User getByEmail(String email);

  User getAuthenticated();

  // VALIDATIONS

  void validateConflictByEmail(String email);

  void validateDTO(UserRegisterDTO userRegisterDTO);
}
