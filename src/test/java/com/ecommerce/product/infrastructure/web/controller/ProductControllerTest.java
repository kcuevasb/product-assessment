package com.ecommerce.product.infrastructure.web.controller;

import com.ecommerce.product.domain.exception.DomainException;
import com.ecommerce.product.domain.service.ProductService;
import com.ecommerce.product.infrastructure.persistence.mapper.ProductMapper;
import com.ecommerce.product.infrastructure.web.dto.FinalProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductController productController;

    @Test
    void getProducts_Success() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Integer brandId = 2;

        ProductDTO productDTO = ProductDTO.builder().build();
        FinalProductDTO finalProductDTO = FinalProductDTO.builder().build();

        when(productService.find(appDate, productId, brandId)).thenReturn(productDTO);
        when(mapper.toFinalDTO(productDTO)).thenReturn(finalProductDTO);

        ResponseEntity<FinalProductDTO> result = productController.getProducts(appDate, productId, brandId);

        assertEquals(finalProductDTO, result.getBody());
    }

    @Test
    void getProducts_ExceptionThrown() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Integer brandId = 2;

        when(productService.find(appDate, productId, brandId)).thenThrow(new DomainException("Product not found"));

        assertThrows(DomainException.class, () -> productController.getProducts(appDate, productId, brandId));
    }

}