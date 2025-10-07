package com.medinastr.security01.service;

import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.exception.DatabaseConflictException;
import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.exception.ObjectNotFoundException;
import com.medinastr.security01.handler.DTOHandler;
import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.dto.response.AuthenticationRequestDTO;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.repository.UserRepository;
import com.medinastr.security01.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtServiceImpl jwtServiceImpl;

  // DATABASE PERSIST

  @Transactional
  public User createCustomer(UserRegisterDTO registerDTO) {
    validateDTO(registerDTO);
    validateConflictByEmail(registerDTO.getEmail());
    User user = buildNewUser(registerDTO);
    return userRepository.save(user);
  }

  public String login(AuthenticationRequestDTO requestDTO) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword()));
      User user = getByEmail(requestDTO.getEmail());
      return jwtServiceImpl.generateToken(user.getEmail());
    } catch (ObjectNotFoundException exc) {
      log.error("Login failed to email {}", requestDTO.getEmail());
      throw new AuthException("error.login.credentials");
    }
  }

  public User buildNewUser(UserRegisterDTO registerDTO) {
    return User.builder()
        .email(registerDTO.getEmail())
        .password(passwordEncoder.encode(registerDTO.getPassword()))
        .role(registerDTO.getRole())
        .build();
  }

  // READ

  public User getByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new ObjectNotFoundException("error.user.notFound"));
  }

  // VALIDATIONS

  public void validateConflictByEmail(String email) {
    Optional<User> optionalCustomer = userRepository.findByEmail(email);
    if (optionalCustomer.isPresent()) {
      throw new DatabaseConflictException("User with this email already exists.");
    }
  }

  public void validateDTO(UserRegisterDTO userRegisterDTO) {
    List<String> errorsMessagesList = DTOHandler.handle(userRegisterDTO);
    if (!errorsMessagesList.isEmpty()) {
      throw new InvalidDTOException(errorsMessagesList);
    }
  }
}
