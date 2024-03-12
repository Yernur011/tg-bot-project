package com.example.parser.kafka.controller;

import com.example.parser.kafka.dto.Product;
import com.example.parser.kafka.dto.UsersProducts;
import com.example.parser.kafka.dto.UsersQuery;
import com.example.parser.kafka.sevice.UsersProductsService;
import com.example.parser.kafka.sevice.kafka.KafkaSenderExample;
import com.example.parser.kafka.sevice.ProductService;
import com.example.parser.kafka.sevice.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class WebController {

    private final KafkaSenderExample kafkaSenderExample;
    private final UserQueryService userQueryService;
    private final ProductService service;
    private final UsersProductsService usersProductsService;

    @GetMapping

    public Flux<UsersQuery> hello(){
        System.out.println(userQueryService.findAllQueries());
        return userQueryService.findAllQueries();
    }
    @GetMapping("/allProducts")
    private Flux<Product> queries(){
        System.out.println(userQueryService.findAllQueries());
        return service.getAllProducts();
    }

    @GetMapping("/delete")
    public void clearDataBase(){
        service.deleteProduct();
        userQueryService.deleteDataBase();
    }
    @GetMapping("/allUsersProducts")
    public Flux<UsersProducts> usersProductsFlux(){
        return usersProductsService.findAllUsersProduct();
    }

}
