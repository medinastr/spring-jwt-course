package com.medinastr.security01.service;

import com.medinastr.security01.exception.DatabaseConflictException;
import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.exception.ObjectNotFoundException;
import com.medinastr.security01.handler.DTOHandler;
import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.repository.UserRepository;
import com.medinastr.security01.service.interfaces.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // DATABASE PERSIST

  public User createNewUser(UserRegisterDTO registerDTO) {
    User user = User.builder()
        .email(registerDTO.getEmail())
        .password(passwordEncoder.encode(registerDTO.getPassword()))
        .role(registerDTO.getRole())
        .build();
    return userRepository.save(user);
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
