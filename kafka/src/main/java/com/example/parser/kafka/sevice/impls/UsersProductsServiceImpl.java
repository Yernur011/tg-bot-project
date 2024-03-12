package com.example.parser.kafka.sevice.impls;


import com.example.parser.kafka.dto.Product;
import com.example.parser.kafka.dto.UsersProducts;
import com.example.parser.kafka.repository.UsersProductsRepository;
import com.example.parser.kafka.sevice.UsersProductsService;
import jakarta.servlet.ServletOutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class UsersProductsServiceImpl implements UsersProductsService {
    private final UsersProductsRepository usersProductsRepository;
    @Override
    public Flux<UsersProducts> findAllUsersProduct() {
        return usersProductsRepository.findAll();
    }

    @Override
    public Mono<Boolean> doesProductExist(Product product, Long chatId) {
        return usersProductsRepository.findById(chatId)
                .map(usersProducts -> usersProducts.getProductList().contains(product));// Возвращаем false, если пользователь с chatId не найден

    }
    @Override
    public Mono<UsersProducts> save(Mono<UsersProducts> usersProducts) {
        System.out.println("user product saving");
        return usersProducts.flatMap(usersProductsRepository::save);
    }


    @Override
    public Mono<UsersProducts> findByUserId(Long chatId) {
        return usersProductsRepository.findById(chatId);
    }

}
