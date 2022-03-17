package com.cap.pocvng.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

public class ElasticConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9300")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
