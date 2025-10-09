package com.medinastr.security01.security;

import com.medinastr.security01.enums.SystemContext;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.service.JwtServiceImpl;
import com.medinastr.security01.service.interfaces.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

  private final JwtServiceImpl jwtServiceImpl;
  private final UserService userService;

  public JWTAuthenticationFilter(JwtServiceImpl jwtServiceImpl, @Lazy UserService userService) {
    this.jwtServiceImpl = jwtServiceImpl;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7); // Extract token after "Bearer "
    username = jwtServiceImpl.extractUsername(jwt);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      User requester = userService.getByEmail(username);
      if (jwtServiceImpl.isTokenValid(jwt, username)) {
        CustomAuthentication authToken =
            new CustomAuthentication(
                requester.getLastLoginAccount().getId(),
                SystemContext.WEB,
                username,
                null,
                requester.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        response.setHeader("Authorization", "Bearer " + jwt);
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
