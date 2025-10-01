package com.medinastr.security01.handler;

import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.exception.DatabaseConflictException;
import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.model.dto.response.ServerResponse;
import com.medinastr.security01.utils.ServerResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(InvalidDTOException.class)
  public ResponseEntity<ServerResponse<?>> handleBadRequest(
      InvalidDTOException exc, HttpServletRequest request) {
    String messages = getMessagesString(exc.getErrorsMessages());
    return ServerResponseUtils.error(messages, HttpStatus.BAD_REQUEST, request.getRequestURI());
  }

  @ExceptionHandler(DatabaseConflictException.class)
  public ResponseEntity<ServerResponse<?>> handleDatabaseConflict(
      DatabaseConflictException exc, HttpServletRequest request) {
    String message = getMessage(exc.getMessage());
    return ServerResponseUtils.error(message, HttpStatus.CONFLICT, request.getRequestURI());
  }

  @ExceptionHandler(AuthException.class)
  public ResponseEntity<ServerResponse<?>> handleAuthException(
      AuthException exc, HttpServletRequest request) {
    String message = getMessage(exc.getMessage());
    return ServerResponseUtils.error(message, HttpStatus.UNAUTHORIZED, request.getRequestURI());
  }

  private String getMessage(String messageCode) {
    return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
  }

  private String getMessagesString(List<String> messagesCodesList) {
    List<String> messages = messagesCodesList.stream().map(this::getMessage).toList();
    return String.join(", ", messages);
  }
}
