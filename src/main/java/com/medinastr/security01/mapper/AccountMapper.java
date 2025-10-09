package com.medinastr.security01.mapper;

import com.medinastr.security01.model.dto.response.AccountResponseDTO;
import com.medinastr.security01.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = "spring")
public interface AccountMapper {

  AccountResponseDTO toResponseDTO(Account account);
}
