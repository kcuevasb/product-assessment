package com.ecommerce.product.domain.service;

import com.ecommerce.product.infrastructure.web.dto.ProductDTO;
import java.time.LocalDateTime;

public interface ProductService {
    ProductDTO find(LocalDateTime appDate, Long productId, Integer brandId);
}
