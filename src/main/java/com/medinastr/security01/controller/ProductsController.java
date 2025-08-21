package com.medinastr.security01.controller;

import com.medinastr.security01.mapper.ProductsMapper;
import com.medinastr.security01.model.dto.response.ProductsResponseDTO;
import com.medinastr.security01.model.dto.response.ServerResponse;
import com.medinastr.security01.model.entity.Products;
import com.medinastr.security01.service.ProductsService;
import com.medinastr.security01.utils.ServerResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ProductsController {

  private final ProductsService productsService;
  private final ProductsMapper productsMapper;

  @GetMapping("/products")
  public CompletableFuture<ResponseEntity<ServerResponse<List<ProductsResponseDTO>>>> getProducts(
      HttpServletRequest request) {
    Page<Products> productsPage = productsService.getProductsPage(0, 15);
    List<ProductsResponseDTO> response = productsPage.stream()
            .map(productsMapper::toResponseDTO).toList();
    return CompletableFuture.supplyAsync(
        () ->
            ServerResponseUtils.success(
                "Products page return successfully",
                HttpStatus.OK,
                request.getRequestURI(),
                response));
  }
}
