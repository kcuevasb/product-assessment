package com.ecommerce.product.infrastructure.persistence.mapper;

import com.ecommerce.product.infrastructure.persistence.model.PriceEntity;
import com.ecommerce.product.infrastructure.web.dto.FinalProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductPriceDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Long productId, Integer brandId, List<PriceEntity> productPriceEntities) {
        return ProductDTO.builder()
                .productId(productId)
                .brandId(brandId)
                .productPrices(mapToProductPriceDTOList(productPriceEntities))
                .build();
    }

    private List<ProductPriceDTO> mapToProductPriceDTOList(List<PriceEntity> priceEntities) {
        return priceEntities.stream()
                .map(this::mapToProductPriceDTO)
                .collect(Collectors.toList());
    }

    private ProductPriceDTO mapToProductPriceDTO(PriceEntity priceEntity) {
        return ProductPriceDTO.builder()
                .priceList(priceEntity.getPriceList())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .priority(priceEntity.getPriority())
                .price(priceEntity.getPrice().doubleValue())
                .curr(priceEntity.getCurrency())
                .build();
    }

    public FinalProductDTO toFinalDTO(ProductDTO product) {
        return FinalProductDTO.builder()
                .finalPrice(String.valueOf(product.getFinalPrice().getPrice()))
                .productId(String.valueOf(product.getProductId()))
                .brandId(String.valueOf(product.getBrandId()))
                .startAppDate(product.getFinalPrice().getStartDate())
                .endAppDate(product.getFinalPrice().getEndDate())
                .priceList(String.valueOf(product.getFinalPrice().getPriceList())).build();
    }
}
