package com.medinastr.security01.model.entity;

import com.medinastr.security01.enums.AuthoritiesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private AuthoritiesEnum role;

  @OneToOne
  @JoinColumn(name = "last_login_account_id")
  private Account lastLoginAccount;

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(role);
  }

  public String getUsername() {
    return this.email;
  }
}
