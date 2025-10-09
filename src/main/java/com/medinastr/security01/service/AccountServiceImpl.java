package com.medinastr.security01.service;

import com.medinastr.security01.model.entity.Account;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.repository.AccountRepository;
import com.medinastr.security01.service.interfaces.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  @Transactional
  public Account createAccount(User requester) {
    Account account =
        Account.builder().secretNumber(UUID.randomUUID().toString()).user(requester).build();
    return accountRepository.save(account);
  }
}
