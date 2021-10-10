package com.company.claimservice.infrastructure.config;

import com.company.kafka.PolicyClosedEvent;
import com.company.kafka.PremiumCreatedEvent;
import com.company.kafka.config.KafkaConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerContainerFactory;

@Configuration
class PremiumCreationErrorConsumerConfig {

    @Bean
    KafkaListenerContainerFactory kafkaPremiumCreationErrorListenerContainerFactory(@Value("${brokerURL}") String brokerURL, @Value("${premiumCreationErrorGroup.id}") String groupId) {
        KafkaConsumerConfig<PremiumCreatedEvent> consumerConfig = new KafkaConsumerConfig<>(brokerURL, groupId);
        return consumerConfig.kafkaListenerContainerFactory();
    }
}
