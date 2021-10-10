package com.company;

import java.math.BigDecimal;

public class PremiumDto extends AbstractDto {

    private Long id;
    private String status;
    private BigDecimal amount;
    private String policyNumber;

    private PremiumDto() {
    }

    public PremiumDto(Long id, String status, BigDecimal amount, String policyNumber) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.policyNumber = policyNumber;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }
}
