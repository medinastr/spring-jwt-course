package com.medinastr.security01.utils;

import com.medinastr.security01.model.dto.response.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServerResponseUtils {

  public static ResponseEntity<ServerResponse<?>> error(
      String message, HttpStatus status, String path) {
    return ResponseEntity.status(status)
        .body(new ServerResponse<>(message, status.value(), path, null));
  }

  public static <P> ResponseEntity<ServerResponse<P>> success(
      String message, HttpStatus status, String path, P payload) {
    return ResponseEntity.status(status)
        .body(new ServerResponse<>(message, status.value(), path, payload));
  }
}
