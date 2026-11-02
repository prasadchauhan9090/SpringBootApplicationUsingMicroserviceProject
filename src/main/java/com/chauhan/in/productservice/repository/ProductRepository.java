package com.chauhan.in.productservice.repository;

import com.chauhan.in.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
