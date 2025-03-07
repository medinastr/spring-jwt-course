package com.medinastr.security01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contacts")
    public ResponseEntity<String> saveContactInquiryDetails() {
        return ResponseEntity.status(HttpStatus.OK).body("Inquiry details saved.");
    }
}
