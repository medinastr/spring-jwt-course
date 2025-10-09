package com.medinastr.security01.service.interfaces;


public interface JwtService {

  public String extractUsername(String token);

  public boolean isTokenValid(String token, String username);

  public String generateToken(String username);
}
