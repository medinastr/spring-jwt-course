package com.medinastr.security01.api;

import com.medinastr.security01.model.dto.request.AuthenticationResponseDTO;
import com.medinastr.security01.model.dto.request.CustomerRegisterDTO;
import com.medinastr.security01.model.dto.response.AuthenticationRequestDTO;
import com.medinastr.security01.model.dto.response.ServerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/auth")
@Tag(
    name = "Authentication",
    description = "Operations related to system authentication (login and register)")
public interface AuthenticationApi {

  @Operation(
      summary = "Register a new user",
      description = "Creates a new user account with the provided details.")
  @PostMapping("/register")
  public CompletableFuture<ResponseEntity<?>> registerUser(
      @RequestBody CustomerRegisterDTO customerRegisterDTO, HttpServletRequest request);

  @Operation(
      summary = "Login a user",
      description = "User login authentication by email and password.")
  @PostMapping("/login")
  public CompletableFuture<ResponseEntity<ServerResponse<AuthenticationResponseDTO>>> login(
      @RequestBody AuthenticationRequestDTO requestDTO, HttpServletRequest request);
}
