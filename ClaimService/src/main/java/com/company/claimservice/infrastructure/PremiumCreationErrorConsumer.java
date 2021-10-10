package com.company.claimservice.infrastructure;

import com.company.claimservice.application.ClaimManager;
import com.company.kafka.PolicyClosedEvent;
import com.company.kafka.PremiumCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class PremiumCreationErrorConsumer {


    private final ClaimManager claimManager;

    private static final Logger log = LoggerFactory.getLogger(PremiumCreationErrorConsumer.class);

    PremiumCreationErrorConsumer(ClaimManager claimManager) {
        this.claimManager = claimManager;
    }

    @KafkaListener(topics = "${premiumCreationError.topic}", containerFactory = "kafkaPremiumCreationErrorListenerContainerFactory")
    void consume(PremiumCreatedEvent event) {
        log.info("Consumed event {}", event);
        this.claimManager.removeClaim(event.getPolicyNumber());
    }
}
