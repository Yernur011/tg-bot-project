package com.example.parser.kafka.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.scheduling.annotation.Scheduled;

@Data
@Slf4j
@Document(indexName = "user_queries")
public class UsersQuery {
    private Long chatId;
    private String username;
    private Query query;
}