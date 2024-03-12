package com.example.parser.kafka.sevice.impls;


import com.example.parser.kafka.dto.Product;
import com.example.parser.kafka.repository.ProductRepository;
import com.example.parser.kafka.sevice.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Mono<Product> saveProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean doesProductExist(String title) {
        Optional<Product> productOptional = productRepository.findByProductName(title);
        return productOptional.isPresent();
    }

    @Override
    public void deleteProduct() {
        productRepository.deleteAll().block();
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
