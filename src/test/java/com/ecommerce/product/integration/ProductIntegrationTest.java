package com.ecommerce.product.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void integrationTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/product-assessment/search")
                .param("appDate", "2020-06-16T21:00:00")
                .param("productId", String.valueOf((Long) 35455L))
                .param("brandId", String.valueOf((Integer) 1)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice").value("38.95"));
    }
}
