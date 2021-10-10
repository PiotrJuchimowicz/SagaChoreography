package com.company.policyservice.application;

import com.company.PolicyDto;
import com.company.kafka.EventStatus;
import com.company.kafka.PolicyClosedEvent;
import com.company.policyservice.domain.Policy;
import com.company.policyservice.domain.PolicyStatus;
import com.company.policyservice.infrastructure.PolicyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PolicyManager {

    private final PolicyRepository policyRepository;
    private final KafkaTemplate<String, PolicyClosedEvent> kafkaTemplate;
    private final String policyCloseTopic;

    PolicyManager(PolicyRepository policyRepository,
                  KafkaTemplate<String, PolicyClosedEvent> kafkaTemplate, @Value("${policyClosed.topic}") String policyCloseTopic) {
        this.policyRepository = policyRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.policyCloseTopic = policyCloseTopic;
    }

    @Transactional
    public void activate(PolicyDto dto) {
        final var policy = new Policy(null, UUID.randomUUID().toString(), null, dto.getProductCode(),
                        PolicyStatus.ACTIVE, dto.getStartDate(), dto.getEndDate(), dto.getInsurerFullName(), 0L);
        policy.calculateInsuranceSum();
        this.policyRepository.save(policy);
    }

    @Transactional
    public void close(String policyNumber) {
        var policyOptional = this.policyRepository.getPolicyByPolicyNumber(policyNumber);
        if(policyOptional.isPresent()) {
            Policy policy = policyOptional.get();
            policy.close();
            kafkaTemplate.send(policyCloseTopic, new PolicyClosedEvent(policyNumber, policy.getInsuranceSum(), EventStatus.SUCCESS));
        }
    }

    @Transactional
    public void cancelClose(String policyNumber) {
        var policyOptional = this.policyRepository.getPolicyByPolicyNumber(policyNumber);
        if(policyOptional.isPresent()) {
            Policy policy = policyOptional.get();
            policy.cancelClose();
        }
    }

    @Transactional(readOnly = true)
    public PolicyDto get(String policyNumber) {
        var policy = this.policyRepository.getPolicyByPolicyNumber(policyNumber).orElseThrow();
        return PolicyMapper.toDto(policy);
    }

}
