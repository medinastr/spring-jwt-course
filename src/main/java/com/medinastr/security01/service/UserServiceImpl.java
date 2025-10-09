package com.medinastr.security01.service;

import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.exception.DatabaseConflictException;
import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.exception.ObjectNotFoundException;
import com.medinastr.security01.handler.DTOHandler;
import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.entity.Account;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.repository.UserRepository;
import com.medinastr.security01.service.interfaces.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // DATABASE PERSIST

  @Override
  public User createNewUser(UserRegisterDTO registerDTO) {
    User user =
        User.builder()
            .email(registerDTO.getEmail())
            .password(passwordEncoder.encode(registerDTO.getPassword()))
            .role(registerDTO.getRole())
            .build();
    return userRepository.save(user);
  }

  @Override
  public User saveLastLoginAccount(User user, Account account) {
    user.setLastLoginAccount(account);
    return userRepository.save(user);
  }

  // READ

  public User getByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new ObjectNotFoundException("error.user.notFound"));
  }

  @Override
  public User getAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    try {
      String email = authentication.getName();
      return getByEmail(email);

    } catch (ObjectNotFoundException e) {
      log.error("Authenticated user not found: {}", e.getMessage());
      throw new AuthException("Authenticated user not found.");
    } catch (Exception e) {
      log.error("Error getting authenticated user: {}", e.getMessage());
      throw new AuthException("Error getting authenticated user.");
    }
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
