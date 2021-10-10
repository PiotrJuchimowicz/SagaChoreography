package com.company.claimservice.infrastructure.config;

import com.company.kafka.ClaimCreatedEvent;
import com.company.kafka.config.KafkaProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
class ClaimCreatedProducerConfig {

    @Bean
    KafkaTemplate<String, ClaimCreatedEvent> kafkaTemplate(@Value("${brokerURL}") String brokerURL) {
        KafkaProducerConfig<ClaimCreatedEvent> kafkaProducerConfig = new KafkaProducerConfig<>(brokerURL);
        return kafkaProducerConfig.kafkaTemplate();
    }
}
