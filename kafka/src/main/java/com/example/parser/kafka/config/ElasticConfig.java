package com.example.parser.kafka.config;

import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;

@Configuration
public class ElasticConfig extends ElasticsearchConfiguration {
    @Value("${password}")
    private String ELASTIC_PASSWORD;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedToLocalhost()
                .usingSsl(buildSSLContext())
                .withBasicAuth("elastic", ELASTIC_PASSWORD)
                .build();
    }

    private SSLContext buildSSLContext() {
        try{
            return new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
