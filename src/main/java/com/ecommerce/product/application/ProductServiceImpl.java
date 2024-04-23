package com.ecommerce.product.application;

import com.ecommerce.product.domain.exception.DomainException;
import com.ecommerce.product.infrastructure.persistence.mapper.ProductMapper;
import com.ecommerce.product.infrastructure.persistence.model.PriceEntity;
import com.ecommerce.product.domain.port.PriceRepository;
import com.ecommerce.product.domain.service.ProductService;
import com.ecommerce.product.infrastructure.web.dto.ProductDTO;
import com.ecommerce.product.infrastructure.web.dto.ProductPriceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final PriceRepository priceRepository;

    private final ProductMapper mapper;

    @Override
    public ProductDTO find(LocalDateTime appDate, Long productId, Integer brandId) {
        try {
            List<PriceEntity> prioritizedPrices = this.priceRepository.find(appDate, productId, brandId);

            ProductDTO productDTO = this.mapProductDTO(prioritizedPrices, productId, brandId);

            this.calculateFinalProduct(productDTO);

            if (Objects.isNull(productDTO.getFinalPrice()) || productDTO.getFinalPrice().getPrice() == 0.0) {
                log.error("No se ha encontrado precio final para el ID {}", productId);
                throw new DomainException("Products not found");
            }

            return productDTO;
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    private ProductDTO mapProductDTO(List<PriceEntity> prioritizedPrices, Long productId, Integer brandId) {
        return Optional.ofNullable(prioritizedPrices)
                .filter(prices -> !prices.isEmpty())
                .map(price -> this.mapper.toDTO(productId, brandId, price))
                .orElseThrow(() -> {
                            log.debug("No se ha encontrado producto para el ID {}", productId);
                            return new DomainException("Products not found");
                        }
                );
    }

    private void calculateFinalProduct(ProductDTO product) {
        product.setFinalPrice(Optional.ofNullable(product.getProductPrices())
                .orElse(new ArrayList<>())
                .stream()
                .max(Comparator.comparing(ProductPriceDTO::getPriority))
                .orElse(ProductPriceDTO.builder().build()));
    }
}
