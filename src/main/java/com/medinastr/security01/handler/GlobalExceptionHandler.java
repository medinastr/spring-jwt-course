package com.medinastr.security01.handler;

import com.medinastr.security01.exception.InvalidDTOException;
import com.medinastr.security01.model.dto.response.ServerResponse;
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
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ServerResponse.builder()
                        .message(messages)
                        .status(400)
                        .path(request.getRequestURI())
                        .payload(null)
                        .build()
                );
    }
}
