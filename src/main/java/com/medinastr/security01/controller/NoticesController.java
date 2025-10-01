package com.medinastr.security01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

  @GetMapping("/notices")
  public ResponseEntity<String> getNotices() {
    return ResponseEntity.status(HttpStatus.OK).body("Here are the notices details from the DB");
  }
}
