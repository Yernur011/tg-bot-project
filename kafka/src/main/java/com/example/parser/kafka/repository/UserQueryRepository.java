package com.example.parser.kafka.repository;

import com.example.parser.kafka.dto.UsersQuery;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface UserQueryRepository extends ReactiveElasticsearchRepository<UsersQuery, Long> {

}
