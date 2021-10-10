package com.company.claimservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "CLAIM_T")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "policy_number")
    private String policyNumber;
    @Enumerated(EnumType.STRING)
    private ClaimStatus status;
    @Column(name = "amount_to_return")
    private BigDecimal amountToReturn;
    @Column(name = "open_date")
    private LocalDate openDate;
    @Column(name = "close_date")
    private LocalDate closeDate;

    @Version
    @Column(nullable = false)
    private Long version;

    private Claim() {
    }

    public Claim(Long id, String policyNumber, ClaimStatus status, BigDecimal amountToReturn,
                 LocalDate openDate, LocalDate closeDate, Long version) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.status = status;
        this.amountToReturn = amountToReturn;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public BigDecimal getAmountToReturn() {
        return amountToReturn;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void calculateAmountToReturn(BigDecimal insuranceSum) {
        this.amountToReturn = insuranceSum.divide(BigDecimal.valueOf(Math.random() + 2), RoundingMode.DOWN);
    }
}
