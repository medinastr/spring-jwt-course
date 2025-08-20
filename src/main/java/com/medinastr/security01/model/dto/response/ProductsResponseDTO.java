package com.medinastr.security01.model.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductsResponseDTO {

  private Long id;

  private String name;

  private String description;

  private BigDecimal price;
}
