package com.medinastr.security01.security;

import com.medinastr.security01.enums.SystemContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

  private Long accountId;

  private SystemContext context;

  public CustomAuthentication(
      Long accountId,
      SystemContext context,
      Object principal,
      Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
    this.accountId = accountId;
    this.context = context;
  }
}
