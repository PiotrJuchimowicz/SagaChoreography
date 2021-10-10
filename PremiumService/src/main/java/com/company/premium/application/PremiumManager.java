package com.company.premium.application;

import com.company.PremiumDto;
import com.company.kafka.EventStatus;
import com.company.kafka.PremiumCreatedEvent;
import com.company.premium.domain.Premium;
import com.company.premium.domain.PremiumStatus;
import com.company.premium.infrastructure.PremiumRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremiumManager {

    private final PremiumRepository orderRepository;
    private final KafkaTemplate<String, PremiumCreatedEvent> kafkaTemplate;
    private final String premiumCreationErrorTopic;

    PremiumManager(PremiumRepository premiumRepository,
                   KafkaTemplate<String, PremiumCreatedEvent> kafkaTemplate, @Value("${premiumCreationError.topic}") String premiumCreationErrorTopic) {
        this.orderRepository = premiumRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.premiumCreationErrorTopic = premiumCreationErrorTopic;
    }

    @Transactional
    public void createPremium(String policyNumber, BigDecimal amountToReturn) {
        try {
            var returnOrder = new Premium(null, PremiumStatus.RETURN, amountToReturn, policyNumber);
            this.orderRepository.save(returnOrder);
            throw new IllegalStateException();
        } catch (Exception e) {
            this.kafkaTemplate.send(premiumCreationErrorTopic, new PremiumCreatedEvent(policyNumber, EventStatus.FAILURE));
        }
    }

    public List<PremiumDto> getByPolicy(String policyNumber) {
        var orders = this.orderRepository.findAllByPolicyNumber(policyNumber);
        return orders.stream()
                .map(it -> new PremiumDto(it.getId(), it.getStatus().name(), it.getAmount(), it.getPolicyNumber()))
                .collect(Collectors.toList());
    }
}
