package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PolicyDto extends AbstractDto {

    private Long id;
    private String policyNumber;
    private BigDecimal insuranceSum;
    private String productCode;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String insurerFullName;

    private PolicyDto() {
    }

    public PolicyDto(Long id, String policyNumber, BigDecimal insuranceSum, String productCode, String status,
                     LocalDate startDate, LocalDate endDate, String insurerFullName) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.insuranceSum = insuranceSum;
        this.productCode = productCode;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insurerFullName = insurerFullName;
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

    public String getStatus() {
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
