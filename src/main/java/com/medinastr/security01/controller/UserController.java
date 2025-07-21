package com.medinastr.security01.controller;

import com.medinastr.security01.model.dto.CustomerRegisterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/customer")
    public ResponseEntity<Void> registerUser(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
