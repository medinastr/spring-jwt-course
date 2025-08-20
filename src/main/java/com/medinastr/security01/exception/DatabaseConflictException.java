package com.medinastr.security01.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class DatabaseConflictException extends RuntimeException {

  public DatabaseConflictException() {}

  public DatabaseConflictException(String message) {
    super(message);
  }
}
