package com.company.claimservice.infrastructure;

import com.company.claimservice.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Optional<Claim> findByPolicyNumber(String policyNumber);
}
