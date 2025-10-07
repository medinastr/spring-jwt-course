package com.medinastr.security01.service;

import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.exception.ObjectNotFoundException;
import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.dto.response.AuthenticationRequestDTO;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.service.interfaces.AuthenticationService;
import com.medinastr.security01.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtServiceImpl jwtServiceImpl;

  @Override
  @Transactional
  public User register(UserRegisterDTO registerDTO) {
    userService.validateDTO(registerDTO);
    userService.validateConflictByEmail(registerDTO.getEmail());
    return userService.createNewUser(registerDTO);
  }

  @Override
  public String login(AuthenticationRequestDTO requestDTO) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword()));
      User user = userService.getByEmail(requestDTO.getEmail());
      return jwtServiceImpl.generateToken(user.getEmail());
    } catch (ObjectNotFoundException exc) {
      log.error("Login failed to email {}", requestDTO.getEmail());
      throw new AuthException("error.login.credentials");
    }
  }
}
