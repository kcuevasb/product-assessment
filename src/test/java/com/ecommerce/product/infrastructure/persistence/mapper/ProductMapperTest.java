package com.ecommerce.product.infrastructure.persistence.mapper;

import com.ecommerce.product.infrastructure.persistence.model.PriceEntity;
import com.ecommerce.product.infrastructure.web.dto.FinalProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductPriceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    @InjectMocks
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToDTO() {
        Long productId = 35555L;
        Integer brandId = 1;
        List<PriceEntity> priceEntities = Collections.singletonList(createPriceEntity());

        ProductDTO result = productMapper.toDTO(productId, brandId, priceEntities);

        assertEquals(productId, result.getProductId());
        assertEquals(brandId, result.getBrandId());
        assertEquals(1, result.getProductPrices().size());
    }

    @Test
    void testToFinalDTO() {
        ProductDTO productDTO = createProductDTO();

        FinalProductDTO result = productMapper.toFinalDTO(productDTO);

        assertEquals(productDTO.getFinalPrice().getPrice().toString(), result.getFinalPrice());
        assertEquals(productDTO.getProductId().toString(), result.getProductId());
        assertEquals(productDTO.getBrandId().toString(), result.getBrandId());
        assertEquals(productDTO.getFinalPrice().getStartDate(), result.getStartAppDate());
        assertEquals(productDTO.getFinalPrice().getEndDate(), result.getEndAppDate());
        assertEquals(productDTO.getFinalPrice().getPriceList().toString(), result.getPriceList());
    }

    private PriceEntity createPriceEntity() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPriceList(1L);
        priceEntity.setStartDate(LocalDateTime.now());
        priceEntity.setEndDate(LocalDateTime.now().plusDays(1));
        priceEntity.setPriority(1);
        priceEntity.setPrice(BigDecimal.valueOf(19.99));
        priceEntity.setCurrency("EUR");
        return priceEntity;
    }

    private ProductDTO createProductDTO() {
        return ProductDTO.builder()
                .finalPrice(ProductPriceDTO.builder()
                        .price(BigDecimal.valueOf(19.99).doubleValue())
                        .startDate(LocalDateTime.now())
                        .endDate(LocalDateTime.now().plusDays(1))
                        .priceList(1L)
                        .build())
                .productId(3555L)
                .brandId(1)
                .build();
    }

}