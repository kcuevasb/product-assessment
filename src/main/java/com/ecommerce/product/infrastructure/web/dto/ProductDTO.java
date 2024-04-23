package com.ecommerce.product.infrastructure.web.dto;

import lombok.*;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private Integer brandId;
    private List<ProductPriceDTO> productPrices;
    private ProductPriceDTO finalPrice;

}
