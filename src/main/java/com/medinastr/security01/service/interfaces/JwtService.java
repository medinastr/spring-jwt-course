package com.medinastr.security01.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

  public String extractUsername(String token);

  public boolean isTokenValid(String token, UserDetails userDetails);

  public String generateToken(String username);
}
