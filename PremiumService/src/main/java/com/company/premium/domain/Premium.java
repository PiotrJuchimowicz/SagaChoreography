package com.company.premium.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PREMIUM_T")
public class Premium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PremiumStatus status;
    private BigDecimal amount;
    @Column(name = "policy_number")
    private String policyNumber;

    private Premium() {
    }

    public Premium(Long id, PremiumStatus status, BigDecimal amount, String policyNumber) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.policyNumber = policyNumber;
    }

    public Long getId() {
        return id;
    }

    public PremiumStatus getStatus() {
        return status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }
}
