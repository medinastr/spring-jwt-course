package com.medinastr.security01.config;

import com.medinastr.security01.security.CustomAuthenticationEntryPoint;
import com.medinastr.security01.security.JWTAuthenticationFilter;
import com.medinastr.security01.exception.AuthException;
import com.medinastr.security01.handler.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JWTAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
        .csrf(csrfConfig -> csrfConfig.disable())
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers("/accounts", "/balances", "/loans", "cards")
                    .authenticated()
                    .requestMatchers("/notices", "/contacts", "/error")
                    .permitAll()
                    .requestMatchers("/customer")
                    .permitAll()
                    .requestMatchers("/products")
                    .permitAll());
    http.formLogin(Customizer.withDefaults());
    http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
    http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public CompromisedPasswordChecker compromisedPasswordChecker() {
    return new HaveIBeenPwnedRestApiPasswordChecker();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return ((request, response, exc) -> {
      String message =
          exc instanceof AuthException
              ? MessageSourceAccessor.getNoArgsMessage(exc.getMessage())
              : exc.getMessage();
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType("application/json");
      response.setHeader("WWW-Authenticate", "Authentication failed");
      response.getWriter().write("{\"message\":\"" + message + "\"}");
    });
  }
}
