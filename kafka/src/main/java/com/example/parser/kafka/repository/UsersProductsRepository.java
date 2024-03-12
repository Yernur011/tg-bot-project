package com.example.parser.kafka.repository;

import com.example.parser.kafka.dto.UsersProducts;
import org.apache.catalina.User;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Mono;

public interface UsersProductsRepository extends ReactiveElasticsearchRepository<UsersProducts, Long> {



}
