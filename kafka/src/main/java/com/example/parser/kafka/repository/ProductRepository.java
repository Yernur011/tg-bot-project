package com.example.parser.kafka.repository;


import com.example.parser.kafka.dto.Product;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

import java.util.Optional;

public interface ProductRepository extends ReactiveElasticsearchRepository<Product, String> {
    Optional<Product> findByProductName(String productName);
}
