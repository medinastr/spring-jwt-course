package com.medinastr.security01.security;

import com.medinastr.security01.config.MessageSourceAccessor;
import com.medinastr.security01.exception.AuthException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class  EazyBankAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {

    String message = authException instanceof AuthException
            ? MessageSourceAccessor.getNoArgsMessage(authException.getMessage())
            : authException.getMessage();

    String jsonResponse = String.format("{\"message\":\"%s\"}", message);
    response.getWriter().write(jsonResponse);

    response.setHeader("eazybank-error-reason", "Authentication failed");
    response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    response.setContentType("application/json:charset=UTF-8");
  }
}
