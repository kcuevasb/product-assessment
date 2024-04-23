package com.ecommerce.product.infrastructure.web.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class FinalProductDTO {

    String productId;
    String brandId;
    String priceList;
    LocalDateTime startAppDate;
    LocalDateTime endAppDate;
    String finalPrice;
}
