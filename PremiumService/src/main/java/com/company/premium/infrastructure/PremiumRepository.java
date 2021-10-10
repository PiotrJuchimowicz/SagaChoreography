package com.company.premium.infrastructure;

import com.company.premium.domain.Premium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremiumRepository extends JpaRepository<Premium, Long> {

    List<Premium> findAllByPolicyNumber(String policyNumber);
}
