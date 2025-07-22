package com.medinastr.security01.controller;

import com.medinastr.security01.mapper.CustomerMapper;
import com.medinastr.security01.model.dto.request.CustomerRegisterDTO;
import com.medinastr.security01.model.entity.Customer;
import com.medinastr.security01.service.CustomerService;
import com.medinastr.security01.utils.ServerResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping("/customer")
    public ResponseEntity<?> registerUser(@RequestBody CustomerRegisterDTO customerRegisterDTO, HttpServletRequest request) {
        customerService.validateDTO(customerRegisterDTO);
        customerService.validateConflictByEmail(customerRegisterDTO.getEmail());
        Customer customer = customerMapper.toEntity(customerRegisterDTO);
        customerService.createCustomer(customer);
        return ServerResponseUtils.success(
                "User registered successfully.",
                HttpStatus.CREATED,
                request.getRequestURI(),
                null);
    }
}
