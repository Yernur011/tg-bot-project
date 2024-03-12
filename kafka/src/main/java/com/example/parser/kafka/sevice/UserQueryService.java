package com.example.parser.kafka.sevice;


import com.example.parser.kafka.dto.UsersQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserQueryService {
    Mono<UsersQuery> saveUser(UsersQuery usersQuery);

    Flux<UsersQuery> findAllQueries();

    Mono<Void> deleteDataBase();

}
