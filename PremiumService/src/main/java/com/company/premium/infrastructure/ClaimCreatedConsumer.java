package com.company.premium.infrastructure;

import com.company.kafka.ClaimCreatedEvent;
import com.company.premium.application.PremiumManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
class ClaimCreatedConsumer {

    private final PremiumManager premiumManager;

    private static final Logger log = LoggerFactory.getLogger(ClaimCreatedConsumer.class);

    ClaimCreatedConsumer(PremiumManager premiumManager) {
        this.premiumManager = premiumManager;
    }


    @KafkaListener(topics = "${claimCreated.topic}", containerFactory = "kafkaClaimCreatedListenerContainerFactory")
    void consume(ClaimCreatedEvent event) {
        log.info("Consumed event {}", event);
        this.premiumManager.createPremium(event.getPolicyNumber(), event.getAmountToPayout());
    }
}
