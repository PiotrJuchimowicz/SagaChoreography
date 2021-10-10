package com.company.policyservice.infrastructure.config;

import com.company.kafka.PolicyClosedEvent;
import com.company.kafka.config.KafkaProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
class PolicyEventProducerConfig {

    @Bean
    KafkaTemplate<String, PolicyClosedEvent> kafkaTemplate(@Value("${brokerURL}") String brokerURL) {
        KafkaProducerConfig<PolicyClosedEvent> kafkaProducerConfig  = new KafkaProducerConfig<>(brokerURL);
        return kafkaProducerConfig.kafkaTemplate();
    }
}
