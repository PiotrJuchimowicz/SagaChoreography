package com.company.claimservice.infrastructure.config;

import com.company.kafka.PolicyClosedEvent;
import com.company.kafka.config.KafkaConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerContainerFactory;

@Configuration
class PolicyClosedConsumerConfig {

    @Bean
    KafkaListenerContainerFactory kafkaPolicyClosedListenerContainerFactory(@Value("${brokerURL}") String brokerURL, @Value("${policyClosedGroup.id}") String groupId) {
        KafkaConsumerConfig<PolicyClosedEvent> consumerConfig = new KafkaConsumerConfig<>(brokerURL, groupId);
        return consumerConfig.kafkaListenerContainerFactory();
    }

}
