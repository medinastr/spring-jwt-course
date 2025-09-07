package com.medinastr.security01.controller;

import com.medinastr.security01.api.AuthenticationApi;
import com.medinastr.security01.mapper.CustomerMapper;
import com.medinastr.security01.model.dto.request.CustomerRegisterDTO;
import com.medinastr.security01.service.CustomerService;
import com.medinastr.security01.utils.ServerResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

  private final CustomerService customerService;
  private final CustomerMapper customerMapper;

  // POST -> /customer
  @Override
  public CompletableFuture<ResponseEntity<?>> registerUser(
      @RequestBody CustomerRegisterDTO customerRegisterDTO, HttpServletRequest request) {
    customerService.createCustomer(customerRegisterDTO);
    return CompletableFuture.supplyAsync(
        () ->
            ServerResponseUtils.success(
                "User registered successfully.",
                HttpStatus.CREATED,
                request.getRequestURI(),
                null));
  }
}
