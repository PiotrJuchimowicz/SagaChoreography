package com.company.policyservice.infrastructure;

import com.company.kafka.PolicyClosedEvent;
import com.company.kafka.PremiumCreatedEvent;
import com.company.policyservice.application.PolicyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class PremiumCreationErrorConsumer {


    private final PolicyManager policyManager;

    private static final Logger log = LoggerFactory.getLogger(PremiumCreationErrorConsumer.class);

    PremiumCreationErrorConsumer(PolicyManager policyManager) {
        this.policyManager = policyManager;
    }

    @KafkaListener(topics = "${premiumCreationError.topic}", containerFactory = "kafkaPremiumCreationErrorListenerContainerFactory")
    void consume(PremiumCreatedEvent event) {
        log.info("Consumed event {}", event);
        this.policyManager.cancelClose(event.getPolicyNumber());
    }
}
