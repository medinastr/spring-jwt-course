package com.medinastr.security01.service.interfaces;

import com.medinastr.security01.model.entity.Account;
import com.medinastr.security01.model.entity.User;

public interface AccountService {

  // PERSIST

  Account createAccount(User requester);

  // READ

  Account getAuthenticated();

  Account getById(Long id);
}
