package com.medinastr.security01.service;

import com.medinastr.security01.model.entity.Products;
import com.medinastr.security01.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductsService {

  private final ProductsRepository productsRepository;

  @Cacheable("products")
  public Page<Products> getProductsPage(Integer page, Integer pageSize) {
    page = Objects.nonNull(page) ? page : 0;
    pageSize = Objects.nonNull(pageSize) ? pageSize : 10;
    Pageable pageable = PageRequest.of(page, pageSize);
    return productsRepository.findAll(pageable);
  }
}
