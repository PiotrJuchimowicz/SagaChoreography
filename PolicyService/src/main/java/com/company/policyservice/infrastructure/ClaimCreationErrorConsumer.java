package com.company.policyservice.infrastructure;

import com.company.kafka.ClaimCreatedEvent;
import com.company.policyservice.application.PolicyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class ClaimCreationErrorConsumer {

    private final PolicyManager policyManager;

    private static final Logger log = LoggerFactory.getLogger(ClaimCreationErrorConsumer.class);


    ClaimCreationErrorConsumer(PolicyManager policyManager) {
        this.policyManager = policyManager;
    }

    @KafkaListener(topics = "${claimCreationError.topic}", containerFactory = "kafkaClaimCreationErrorConsumerFactory")
    void consume(ClaimCreatedEvent event) {
        log.info("Consumed event {}", event);
        this.policyManager.cancelClose(event.getPolicyNumber());
    }

}
