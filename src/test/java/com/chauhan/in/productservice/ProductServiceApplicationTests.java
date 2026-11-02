//package com.chauhan.in.productservice;
//
//import com.chauhan.in.productservice.dto.ProductRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.mongodb.MongoDBContainer;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.math.BigDecimal;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//class ProductServiceApplicationTests {
//
//    @Container
//    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry)
//    {
//        dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
//    }
//
//
//
//    @Test
//    void shouldCreateProduct() throws Exception
//    {
//       ProductRequest productRequest =  getProductRequest();
//       String productRequestString =  objectMapper.writeValueAsString(productRequest);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(productRequestString))
//                .andExpect(status().isCreated());
//    }
//
//
//
//    private ProductRequest getProductRequest()
//    {
//        return ProductRequest.builder()
//                .name("iphone 13")
//                .description("iphone 13")
//                .price(BigDecimal.valueOf(1200))
//                .build();
//    }
//
//}


package com.chauhan.in.productservice;

import com.chauhan.in.productservice.dto.ProductRequest;

import com.fasterxml.jackson.databind.ObjectMapper; // ✅ Correct!

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    // 1. Start MongoDB container
    @Container
    static MongoDBContainer mongoDBContainer =
            new MongoDBContainer("mongo:4.4.2");

    // 2. Inject MockMvc
    @Autowired
    private MockMvc mockMvc;

    // 3. Inject ObjectMapper
    @Autowired
    private ObjectMapper objectMapper;

    // 4. Configure MongoDB connection dynamically
    @DynamicPropertySource
    static void setProperties(
            DynamicPropertyRegistry dynamicPropertyRegistry) {

        dynamicPropertyRegistry.add(
                "spring.data.mongodb.uri",
                mongoDBContainer::getReplicaSetUrl
        );
    }

    // 5. Test create product API
    @Test
    void shouldCreateProduct() throws Exception {

        // Create product request
        ProductRequest productRequest = getProductRequest();

        // Convert Java object into JSON string
        String productRequestString =
                objectMapper.writeValueAsString(productRequest);

        // Perform POST request and validate response
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(productRequestString)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("iphone 13"))
                .andExpect(jsonPath("$.description").value("iphone 13"));
    }

    // 6. Prepare test product data
    private ProductRequest getProductRequest() {

        return ProductRequest.builder()
                .name("iphone 13")
                .description("iphone 13")
                .price(BigDecimal.valueOf(1200))
                .build();
    }
}

