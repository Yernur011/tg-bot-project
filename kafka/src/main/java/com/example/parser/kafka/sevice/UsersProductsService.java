package com.example.parser.kafka.sevice;

import com.example.parser.kafka.dto.Product;
import com.example.parser.kafka.dto.UsersProducts;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UsersProductsService {
    Flux<UsersProducts> findAllUsersProduct();
    Mono<Boolean> doesProductExist(Product product, Long chatId);

    Mono<UsersProducts> save(Mono<UsersProducts> usersProducts);

    Mono<UsersProducts> findByUserId(Long chatId);
}
