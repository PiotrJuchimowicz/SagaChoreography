package com.company.premium.infrastructure;

import com.company.kafka.PremiumCreatedEvent;
import com.company.kafka.config.KafkaProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
class PremiumCreationProducerConfig {

    @Bean
    KafkaTemplate<String, PremiumCreatedEvent> kafkaTemplate(@Value("${brokerURL}") String brokerURL) {
        KafkaProducerConfig<PremiumCreatedEvent> kafkaProducerConfig = new KafkaProducerConfig<>(brokerURL);
        return kafkaProducerConfig.kafkaTemplate();
    }
}
