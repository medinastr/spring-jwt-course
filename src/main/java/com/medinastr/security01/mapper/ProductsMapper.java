package com.medinastr.security01.mapper;

import com.medinastr.security01.model.dto.response.ProductsResponseDTO;
import com.medinastr.security01.model.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = "spring")
public interface ProductsMapper {

  ProductsResponseDTO toResponseDTO(Products product);
}
