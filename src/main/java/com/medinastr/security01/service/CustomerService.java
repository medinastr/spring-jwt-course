package com.medinastr.security01.service;

import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.exception.DatabaseConflictException;
import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.exception.ObjectNotFoundException;
import com.medinastr.security01.handler.DTOHandler;
import com.medinastr.security01.model.dto.request.CustomerRegisterDTO;
import com.medinastr.security01.model.dto.response.AuthenticationRequestDTO;
import com.medinastr.security01.model.entity.Customer;
import com.medinastr.security01.repository.CustomerRepository;
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
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  // DATABASE PERSIST

  @Transactional
  public Customer createCustomer(CustomerRegisterDTO registerDTO) {
    validateDTO(registerDTO);
    validateConflictByEmail(registerDTO.getEmail());
    Customer customer = buildNewUser(registerDTO);
    return customerRepository.save(customer);
  }

  public String login(AuthenticationRequestDTO requestDTO) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword()));
      Customer customer = getByEmail(requestDTO.getEmail());
      return jwtService.generateToken(customer.getEmail());
    } catch (ObjectNotFoundException exc) {
      log.error("Login failed to email {}", requestDTO.getEmail());
      throw new AuthException("error.login.credentials");
    }
  }

  public Customer buildNewUser(CustomerRegisterDTO registerDTO) {
    return Customer.builder()
        .email(registerDTO.getEmail())
        .password(passwordEncoder.encode(registerDTO.getPassword()))
        .role(registerDTO.getRole())
        .build();
  }

  // READ

  public Customer getByEmail(String email) {
    return customerRepository
        .findByEmail(email)
        .orElseThrow(() -> new ObjectNotFoundException("error.user.notFound"));
  }

  // VALIDATIONS

  public void validateConflictByEmail(String email) {
    Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
    if (optionalCustomer.isPresent()) {
      throw new DatabaseConflictException("User with this email already exists.");
    }
  }

  public void validateDTO(CustomerRegisterDTO customerRegisterDTO) {
    List<String> errorsMessagesList = DTOHandler.handle(customerRegisterDTO);
    if (!errorsMessagesList.isEmpty()) {
      throw new InvalidDTOException(errorsMessagesList);
    }
  }
}
