package com.medinastr.security01.model.entity;

import com.medinastr.security01.enums.AuthoritiesEnum;
import com.medinastr.security01.enums.UserStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String password;

  private String name;

  @Enumerated(EnumType.STRING)
  private AuthoritiesEnum role;

  @Enumerated(EnumType.STRING)
  private UserStatusEnum status;

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(role);
  }

  @Override
  public boolean isAccountNonExpired() {
    return Objects.equals(UserStatusEnum.ENABLE, status);
  }

  @Override
  public boolean isAccountNonLocked() {
    return Objects.equals(UserStatusEnum.ENABLE, status);
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return Objects.equals(UserStatusEnum.ENABLE, status);
  }

  @Override
  public boolean isEnabled() {
    return Objects.equals(UserStatusEnum.ENABLE, status);
  }
}
