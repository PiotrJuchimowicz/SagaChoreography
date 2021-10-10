package com.company.claimservice.application;

import com.company.ClaimDto;
import com.company.claimservice.domain.Claim;
import com.company.claimservice.domain.ClaimStatus;
import com.company.claimservice.infrastructure.ClaimRepository;
import com.company.kafka.ClaimCreatedEvent;
import com.company.kafka.EventStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Transactional
public class ClaimManager {

    private final ClaimRepository claimRepository;
    private final KafkaTemplate<String, ClaimCreatedEvent> kafkaTemplate;
    private final String claimCreatedTopic;
    private final String claimCreationErrorTopic;

    ClaimManager(ClaimRepository claimRepository,
                 KafkaTemplate<String, ClaimCreatedEvent> kafkaTemplate, @Value("${claimCreated.topic}") String claimCreatedTopic,
                 @Value("${claimCreationError.topic}") String claimCreationErrorTopic) {
        this.claimRepository = claimRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.claimCreatedTopic = claimCreatedTopic;
        this.claimCreationErrorTopic = claimCreationErrorTopic;
    }

    @Transactional
    public void createClaim(String policyNumber, BigDecimal insuranceSum) {
        try {
            Claim claim = new Claim(null, policyNumber, ClaimStatus.OPENED, null, LocalDate.now(), null, 0L);
            claim.calculateAmountToReturn(insuranceSum);
            this.claimRepository.save(claim);
            kafkaTemplate.send(claimCreatedTopic, new ClaimCreatedEvent(claim.getPolicyNumber(), claim.getAmountToReturn(), EventStatus.SUCCESS));
        } catch (Exception e) {
            kafkaTemplate.send(claimCreationErrorTopic, new ClaimCreatedEvent(policyNumber, BigDecimal.ZERO, EventStatus.FAILURE));
        }
    }

    @Transactional
    public void removeClaim(String policyNumber) {
        var claimOptional = this.claimRepository.findByPolicyNumber(policyNumber);
        claimOptional.ifPresent(this.claimRepository::delete);
    }

    public ClaimDto get(String policyNumber) {
        var claim = this.claimRepository.findByPolicyNumber(policyNumber).orElseThrow();
        return new ClaimDto(claim.getId(), claim.getPolicyNumber(), claim.getAmountToReturn(), claim.getStatus().name(),
                claim.getOpenDate(), claim.getCloseDate());

    }
}
