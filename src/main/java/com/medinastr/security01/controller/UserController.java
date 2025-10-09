package com.medinastr.security01.controller;

import com.medinastr.security01.api.UserApi;
import com.medinastr.security01.mapper.UserMapper;
import com.medinastr.security01.model.dto.response.ServerResponse;
import com.medinastr.security01.model.dto.response.UserInfosDTO;
import com.medinastr.security01.model.entity.User;
import com.medinastr.security01.service.interfaces.UserService;
import com.medinastr.security01.utils.ServerResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;
  private final UserMapper userMapper;

  // GET -> /users/info
  @Override
  public ResponseEntity<ServerResponse<UserInfosDTO>> getRequesterInfos(
      HttpServletRequest request) {
    User user = userService.getAuthenticated();
    UserInfosDTO payload = userMapper.toUserInfosDTO(user);
    return ServerResponseUtils.success("Message", HttpStatus.OK, request.getRequestURI(), payload);
  }
}
