package com.medinastr.security01.service;

import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.handler.DTOHandler;
import com.medinastr.security01.model.dto.request.CustomerRegisterDTO;
import com.medinastr.security01.model.entity.Customer;
import com.medinastr.security01.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    // DATABASE PERSIST

    public Customer createCustomer(Customer customer) {
        String hashPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashPassword);
        return customerRepository.save(customer);
    }

    // VALIDATIONS

    public void validateDTO(CustomerRegisterDTO customerRegisterDTO) {
        List<String> errorsMessagesList = DTOHandler.handle(customerRegisterDTO);
        if(!errorsMessagesList.isEmpty()) {
            throw new InvalidDTOException(errorsMessagesList);
        }
    }
}
