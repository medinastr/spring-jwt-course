package com.medinastr.security01.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum AuthoritiesEnum implements GrantedAuthority {
  READ("READ"),
  ADMIN("ADMIN");
  private final String authority;
}
