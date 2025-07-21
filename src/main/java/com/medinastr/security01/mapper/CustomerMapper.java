package com.medinastr.security01.mapper;

import com.medinastr.security01.model.dto.CustomerRegisterDTO;
import com.medinastr.security01.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring"
)
public interface CustomerMapper {

    Customer toEntity(CustomerRegisterDTO dto);
}
