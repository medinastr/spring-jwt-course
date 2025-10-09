package com.medinastr.security01.api;

import com.medinastr.security01.model.dto.response.ServerResponse;
import com.medinastr.security01.model.dto.response.UserInfosDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Tag(name = "Users", description = "Operations related to users.")
public interface UserApi {

  @Operation(
      summary = "Get requester user information",
      description = "Retrieves information about the currently authenticated user.")
  @GetMapping("/info")
  public ResponseEntity<ServerResponse<UserInfosDTO>> getRequesterInfos(
      HttpServletRequest request);
}
