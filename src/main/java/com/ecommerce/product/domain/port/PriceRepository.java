package com.ecommerce.product.domain.port;

import com.ecommerce.product.infrastructure.persistence.model.PriceEntity;
import com.ecommerce.product.infrastructure.persistence.PriceRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends PriceRepositoryImpl {
    List<PriceEntity> find(LocalDateTime appDate, Long productId, Integer brandId);
}
