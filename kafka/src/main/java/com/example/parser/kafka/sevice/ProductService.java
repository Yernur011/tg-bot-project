package com.example.parser.kafka.sevice;


import com.example.parser.kafka.dto.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> saveProduct(Product product);
    Flux<Product> getAllProducts();

    boolean doesProductExist(String title);

    void deleteProduct();
}
