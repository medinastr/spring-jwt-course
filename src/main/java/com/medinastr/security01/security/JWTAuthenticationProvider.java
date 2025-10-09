package com.medinastr.security01.security;

import com.medinastr.security01.enums.SystemContext;
import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    User user = userService.getByEmail(username);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new AuthException("error.authentication.badCredentials");
    }

    return new CustomAuthentication(
        user.getLastLoginAccount().getId(),
        SystemContext.WEB,
        username,
        password,
        user.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
