package com.example.parser.kafka.sevice.kafka;

import com.example.parser.kafka.dto.UsersProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSenderExample {
    private final KafkaTemplate<String, UsersProducts> kafkaTemplate;
    public void sendMessage(String topicName, UsersProducts usersProducts){
        kafkaTemplate.send(topicName, usersProducts);
    }
}