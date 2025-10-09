package com.medinastr.security01.mapper;

import com.medinastr.security01.model.dto.request.UserRegisterDTO;
import com.medinastr.security01.model.dto.response.UserInfosDTO;
import com.medinastr.security01.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = "spring")
public interface UserMapper {

  User toEntity(UserRegisterDTO dto);

  UserInfosDTO toUserInfosDTO(User user);
}
