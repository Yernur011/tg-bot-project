package com.example.parser.kafka.sevice.kafka;

import com.example.parser.kafka.dto.UsersQuery;
import com.example.parser.kafka.sevice.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaListenerExample {
    private final UserQueryService queryService;

    @KafkaListener(topics = "from_telegram", groupId = "myGroup")
    void listener(UsersQuery data) {
        queryService.saveUser(data);
        log.info("data from telegram {}", data);
    }
}
