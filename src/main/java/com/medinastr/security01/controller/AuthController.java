package com.medinastr.security01.controller;

import com.medinastr.security01.mapper.CustomerMapper;
import com.medinastr.security01.model.dto.request.CustomerRegisterDTO;
import com.medinastr.security01.service.CustomerService;
import com.medinastr.security01.utils.ServerResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final CustomerService customerService;

  @PostMapping("/auth")
  @Transactional
  public CompletableFuture<ResponseEntity<?>> registerUser(
      @RequestBody CustomerRegisterDTO customerRegisterDTO, HttpServletRequest request) {
    customerService.validateConflictByEmail(customerRegisterDTO.getEmail());
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
