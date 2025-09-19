package com.medinastr.security01.service;

import com.medinastr.security01.exception.DatabaseConflictException;
import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.handler.DTOHandler;
import com.medinastr.security01.model.dto.request.CustomerRegisterDTO;
import com.medinastr.security01.model.entity.Customer;
import com.medinastr.security01.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;

  // DATABASE PERSIST

  @Transactional
  public Customer createCustomer(CustomerRegisterDTO registerDTO) {
    validateDTO(registerDTO);
    validateConflictByEmail(registerDTO.getEmail());
    Customer customer = buildNewUser(registerDTO);
    return customerRepository.save(customer);
  }

  public Customer buildNewUser(CustomerRegisterDTO registerDTO) {
      return Customer.builder()
              .email(registerDTO.getEmail())
              .password(passwordEncoder.encode(registerDTO.getPassword()))
              .role(registerDTO.getRole())
              .build();
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
