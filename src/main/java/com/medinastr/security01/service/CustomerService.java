package com.medinastr.security01.service;

import com.medinastr.security01.model.entity.Customer;
import com.medinastr.security01.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public Customer createCustomer(Customer customer) {
        String hashPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashPassword);
        return customerRepository.save(customer);
    }
}
