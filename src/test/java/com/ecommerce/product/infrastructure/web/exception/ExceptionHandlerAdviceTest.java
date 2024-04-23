package com.ecommerce.product.infrastructure.web.exception;

import com.ecommerce.product.domain.exception.DomainException;
import com.ecommerce.product.infrastructure.web.dto.ErrorResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerAdviceTest {

    @Mock
    private DomainException domainException;

    @InjectMocks
    private ExceptionHandlerAdvice exceptionHandlerAdvice;

    @Test
    void handleDomainException_ReturnsInternalServerError() {
        when(domainException.getMessage()).thenReturn("Test error message");

        ResponseEntity<ErrorResponseDTO> responseEntity = exceptionHandlerAdvice.handleDomainException(domainException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal Server Error", responseEntity.getBody().getError());
        assertEquals("Test error message", responseEntity.getBody().getMessage());
    }

    @Test
    void handleDomainException_WithNullMessage_ReturnsInternalServerErrorWithDefaultMessage() {
        when(domainException.getMessage()).thenReturn(null);

        ResponseEntity<ErrorResponseDTO> responseEntity = exceptionHandlerAdvice.handleDomainException(domainException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal Server Error", responseEntity.getBody().getError());
        assertEquals(null, responseEntity.getBody().getMessage());
    }

}