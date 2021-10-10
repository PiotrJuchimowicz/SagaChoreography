package com.company.policyservice.application;

import com.company.PolicyDto;
import com.company.policyservice.domain.Policy;

class PolicyMapper {

    private PolicyMapper() {

    }

    static PolicyDto toDto(Policy entity) {
        return new PolicyDto(entity.getId(), entity.getPolicyNumber(), entity.getInsuranceSum(),
                entity.getProductCode(), entity.getStatus().name(), entity.getStartDate(), entity.getEndDate(),
                entity.getInsurerFullName());
    }
}
