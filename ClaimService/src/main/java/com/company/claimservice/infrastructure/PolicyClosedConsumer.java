package com.company.claimservice.infrastructure;

import com.company.claimservice.application.ClaimManager;
import com.company.kafka.PolicyClosedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class PolicyClosedConsumer {

    private final ClaimManager claimManager;

    private static final Logger log = LoggerFactory.getLogger(PolicyClosedConsumer.class);

    PolicyClosedConsumer(ClaimManager claimManager) {
        this.claimManager = claimManager;
    }

    @KafkaListener(topics = "${policyClosed.topic}", containerFactory = "kafkaPolicyClosedListenerContainerFactory")
    void consume(PolicyClosedEvent event) {
        log.info("Consumed event {}", event);
        this.claimManager.createClaim(event.getPolicyNumber(), event.getInsuranceSum());
    }
}
