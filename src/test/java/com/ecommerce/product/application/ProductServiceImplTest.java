package com.ecommerce.product.application;

import com.ecommerce.product.domain.exception.DomainException;
import com.ecommerce.product.domain.port.PriceRepository;
import com.ecommerce.product.infrastructure.persistence.mapper.ProductMapper;
import com.ecommerce.product.infrastructure.persistence.model.PriceEntity;
import com.ecommerce.product.infrastructure.web.dto.FinalProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductPriceDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void find_ProductsFound_CalculateFinalPrice() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Integer brandId = 2;

        List<PriceEntity> prices = Collections.singletonList(new PriceEntity());
        when(priceRepository.find(any(), any(), any())).thenReturn(prices);

        ProductDTO expectedProduct = ProductDTO.builder()
                .productId(productId)
                .brandId(brandId)
                .productPrices(List.of(ProductPriceDTO.builder().price(10.10).build()))
                .build();
        when(mapper.toDTO(anyLong(), anyInt(), anyList())).thenReturn(expectedProduct);

        ProductDTO result = productService.find(appDate, productId, brandId);

        assertNotNull(result);
        assertEquals(expectedProduct, result);
    }

    @Test
    void find_NoProductsFound_ThrowDomainException() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Integer brandId = 2;

        when(priceRepository.find(any(), any(), any())).thenReturn(Collections.emptyList());

        assertThrows(DomainException.class, () -> productService.find(appDate, productId, brandId));
    }

    @Test
    void find_ExceptionInProcessing_ThrowDomainException() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Integer brandId = 2;

        when(priceRepository.find(any(), any(), any())).thenThrow(new RuntimeException("Some error"));

        assertThrows(DomainException.class, () -> productService.find(appDate, productId, brandId));
    }

}