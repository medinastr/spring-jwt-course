package com.medinastr.security01.api;

import com.medinastr.security01.model.dto.response.AccountResponseDTO;
import com.medinastr.security01.model.dto.response.ServerResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/accounts")
public interface AccountApi {

  @PostMapping
  public ResponseEntity<ServerResponse<AccountResponseDTO>> createAccount(
      HttpServletRequest request);
}
