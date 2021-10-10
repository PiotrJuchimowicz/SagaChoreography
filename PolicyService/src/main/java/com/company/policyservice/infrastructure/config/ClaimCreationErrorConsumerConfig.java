package com.company.policyservice.infrastructure.config;

import com.company.kafka.ClaimCreatedEvent;
import com.company.kafka.config.KafkaConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerContainerFactory;

@Configuration
class ClaimCreationErrorConsumerConfig {

    @Bean
    KafkaListenerContainerFactory kafkaClaimCreationErrorConsumerFactory(@Value("${brokerURL}") String brokerURL, @Value("${claimCreationErrorGroup.id}") String groupId) {
        KafkaConsumerConfig<ClaimCreatedEvent> consumerConfig = new KafkaConsumerConfig<>(brokerURL, groupId);
        return consumerConfig.kafkaListenerContainerFactory();
    }
}
