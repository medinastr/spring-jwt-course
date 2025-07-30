package com.medinastr.security01.handler;

import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.exception.DatabaseConflictException;
import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.model.dto.response.ServerResponse;
import com.medinastr.security01.utils.ServerResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDTOException.class)
    public ResponseEntity<ServerResponse<?>> handleBadRequest(InvalidDTOException exc, HttpServletRequest request) {
        String messages = String.join(", ", exc.getErrorsMessages());
        return ServerResponseUtils.error(messages, HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(DatabaseConflictException.class)
    public ResponseEntity<ServerResponse<?>> handleDatabaseConflict(DatabaseConflictException exc, HttpServletRequest request) {
        return ServerResponseUtils.error(exc.getMessage(), HttpStatus.CONFLICT, request.getRequestURI());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ServerResponse<?>> handleAuthException(AuthException exc, HttpServletRequest request) {
        return ServerResponseUtils.error(exc.getMessage(), HttpStatus.UNAUTHORIZED, request.getRequestURI());
    }
}
