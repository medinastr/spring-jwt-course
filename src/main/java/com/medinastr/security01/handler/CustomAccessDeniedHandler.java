package com.medinastr.security01.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {

    String jsonResponse = String.format("{\"message\":\"%s\"}", accessDeniedException.getMessage());

    response.setHeader("eazybank-error-reason", "Authentication failed");
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json:charset=UTF-8");
    response.getWriter().write(jsonResponse);
  }
}
