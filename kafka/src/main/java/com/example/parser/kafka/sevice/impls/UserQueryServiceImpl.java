package com.example.parser.kafka.sevice.impls;

import com.example.parser.kafka.dto.UsersQuery;
import com.example.parser.kafka.repository.UserQueryRepository;
import com.example.parser.kafka.sevice.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserQueryRepository userQueryRepository;

    @Override
    public Mono<UsersQuery> saveUser(UsersQuery usersQuery) {
        return userQueryRepository.save(usersQuery);
    }

    @Override
    public Mono<Void> deleteDataBase() {
        return userQueryRepository.deleteAll();
    }

    @Override
        public Flux<UsersQuery> findAllQueries() {
        return userQueryRepository.findAll();
    }
}
