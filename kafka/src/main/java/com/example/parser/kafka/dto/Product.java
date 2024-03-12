package com.example.parser.kafka.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@Builder
@Document(indexName = "product")
public class Product {
    private String id;
    private String productName;
    private String productLink;
    private String status;
    private String address;
}
