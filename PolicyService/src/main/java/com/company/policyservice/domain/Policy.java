package com.company.policyservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "POLICY")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "insurance_sum")
    private BigDecimal insuranceSum;

    @Column(name = "product_code")
    private String productCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PolicyStatus status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "insurer_full_name")
    private String insurerFullName;

    @Version
    @Column(nullable = false)
    private Long version;

    private Policy() {
    }

    public Policy(Long id, String policyNumber, BigDecimal insuranceSum, String productCode,
                  PolicyStatus status, LocalDate startDate, LocalDate endDate, String insurerFullName, Long version) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.insuranceSum = insuranceSum;
        this.productCode = productCode;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insurerFullName = insurerFullName;
        this.version = version;
    }

    public void close() {
        this.status = PolicyStatus.CLOSED;
    }

    public void cancelClose() {
        if(this.status != PolicyStatus.CLOSED) {
            throw new IllegalStateException();
        }

        this.status = PolicyStatus.ACTIVE;
    }

    public void calculateInsuranceSum() {
        this.insuranceSum = BigDecimal.valueOf(Math.random() * 1000 + 100);
    }

    public Long getId() {
        return id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public BigDecimal getInsuranceSum() {
        return insuranceSum;
    }

    public String getProductCode() {
        return productCode;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getInsurerFullName() {
        return insurerFullName;
    }
}
