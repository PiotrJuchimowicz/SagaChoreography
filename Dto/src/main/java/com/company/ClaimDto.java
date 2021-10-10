package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClaimDto extends AbstractDto {

    private Long id;
    private String policyNumber;
    private BigDecimal amountToReturn;
    private String status;
    private LocalDate openDate;
    private LocalDate closeDate;

    private ClaimDto() {
    }

    public ClaimDto(Long id, String policyNumber, BigDecimal amountToReturn, String status, LocalDate openDate,
                    LocalDate closeDate) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.amountToReturn = amountToReturn;
        this.status = status;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }

    public Long getId() {
        return id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public BigDecimal getAmountToReturn() {
        return amountToReturn;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }
}
