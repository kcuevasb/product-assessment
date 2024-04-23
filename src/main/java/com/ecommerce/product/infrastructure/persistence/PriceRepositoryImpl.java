package com.ecommerce.product.infrastructure.persistence;

import com.ecommerce.product.infrastructure.persistence.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryImpl extends JpaRepository<PriceEntity, Integer> {

    @Query("SELECT p FROM PriceEntity p INNER JOIN p.brand b " +
            "WHERE :appDate BETWEEN p.startDate AND p.endDate " +
            "AND p.productId = :productId AND b.id = :brandId " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> find(
            @Param("appDate") LocalDateTime appDate,
            @Param("productId") Long productId,
            @Param("brandId") Integer brandId);

}
