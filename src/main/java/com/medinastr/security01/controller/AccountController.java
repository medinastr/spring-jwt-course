package com.medinastr.security01.controller;

import com.medinastr.security01.api.AccountApi;
import com.medinastr.security01.mapper.AccountMapper;
import com.medinastr.security01.model.dto.response.AccountResponseDTO;
import com.medinastr.security01.model.dto.response.ServerResponse;
import com.medinastr.security01.model.entity.Account;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.service.interfaces.AccountService;
import com.medinastr.security01.service.interfaces.UserService;
import com.medinastr.security01.utils.ServerResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

  private final AccountService accountService;
  private final UserService userService;
  private final AccountMapper accountMapper;

  // POST -> /accounts
  @Override
  public ResponseEntity<ServerResponse<AccountResponseDTO>> createAccount(
      HttpServletRequest request) {
    User requester = userService.getAuthenticated();
    Account account = accountService.createAccount(requester);
    AccountResponseDTO payload = accountMapper.toResponseDTO(account);
    return ServerResponseUtils.success(
        "Account created successfully", HttpStatus.CREATED, request.getRequestURI(), payload);
  }
}
