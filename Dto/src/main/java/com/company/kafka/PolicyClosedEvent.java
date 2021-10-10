package com.company.kafka;

import com.company.AbstractDto;

import java.math.BigDecimal;

public class PolicyClosedEvent extends AbstractDto {
    private String policyNumber;
    private BigDecimal insuranceSum;
    private EventStatus eventStatus;

    private PolicyClosedEvent() {
    }

    public PolicyClosedEvent(String policyNumber, BigDecimal insuranceSum, EventStatus eventStatus) {
        this.policyNumber = policyNumber;
        this.insuranceSum = insuranceSum;
        this.eventStatus = eventStatus;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public BigDecimal getInsuranceSum() {
        return insuranceSum;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    @Override
    public String toString() {
        return "PolicyClosedEvent{" +
                "policyNumber='" + policyNumber + '\'' +
                ", insuranceSum=" + insuranceSum +
                ", eventStatus=" + eventStatus +
                "} " + super.toString();
    }
}
