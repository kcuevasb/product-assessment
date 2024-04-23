package com.ecommerce.product.infrastructure.web.controller;

import com.ecommerce.product.domain.exception.DomainException;
import com.ecommerce.product.domain.service.ProductService;
import com.ecommerce.product.infrastructure.persistence.mapper.ProductMapper;
import com.ecommerce.product.infrastructure.web.dto.FinalProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product-assessment")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper mapper;

    @GetMapping("/search")
    @Tag(name = "Products")
    @Operation(summary = "Return products filtered by appDate, productId and brandId")
    public ResponseEntity<FinalProductDTO> getProducts(
            @RequestParam("appDate") LocalDateTime appDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Integer brandId) {
        log.info("[PRODUCT_SVC] - Searching for products with ID {}", productId);
        ProductDTO product;
        try {
            product = this.productService.find(appDate, productId, brandId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DomainException(e.getMessage());
        }
        return ResponseEntity.ok(this.mapper.toFinalDTO(product));
    }

}
