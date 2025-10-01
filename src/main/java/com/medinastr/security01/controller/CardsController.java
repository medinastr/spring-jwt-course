package com.medinastr.security01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

  @GetMapping("/cards")
  public ResponseEntity<String> getCardsDetails() {
    return ResponseEntity.status(HttpStatus.OK).body("Here are the cards details from the DB");
  }
}
