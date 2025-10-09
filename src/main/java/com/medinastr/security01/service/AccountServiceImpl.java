package com.medinastr.security01.service;

import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.exception.ObjectNotFoundException;
import com.medinastr.security01.model.entity.Account;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.repository.AccountRepository;
import com.medinastr.security01.security.CustomAuthentication;
import com.medinastr.security01.service.interfaces.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

  @Override
  public Account getAuthenticated() {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
      return getById(customAuthentication.getAccountId());

    } catch (ObjectNotFoundException e) {
      log.error("Authenticated account not found: {}", e.getMessage());
      throw new AuthException("Authenticated account not found");
    } catch (Exception e) {
      log.error("Failed to get user authenticated account: {}", e.getMessage());
      throw new AuthException("Failed to get user authenticated account");
    }
  }

  @Override
  public Account getById(Long id) {
    return accountRepository
        .findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Account not found"));
  }
}
