package com.ecommerce.product.infrastructure.web.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {
    private String error;
    private String message;
}