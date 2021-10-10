package com.company.policyservice.infrastructure;

import com.company.policyservice.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    Optional<Policy> getPolicyByPolicyNumber(String policyNumber);

}
