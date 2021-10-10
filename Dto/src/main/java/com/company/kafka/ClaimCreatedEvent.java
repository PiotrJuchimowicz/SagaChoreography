package com.company.kafka;

import com.company.AbstractDto;

import java.math.BigDecimal;

public class ClaimCreatedEvent extends AbstractDto {
    private String policyNumber;
    private BigDecimal amountToPayout;
    private EventStatus eventStatus;

    private ClaimCreatedEvent() {
    }

    public ClaimCreatedEvent(String policyNumber, BigDecimal amountToPayout, EventStatus eventStatus) {
        this.policyNumber = policyNumber;
        this.amountToPayout = amountToPayout;
        this.eventStatus = eventStatus;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public BigDecimal getAmountToPayout() {
        return amountToPayout;
    }

    @Override
    public String toString() {
        return "ClaimCreatedEvent{" +
                "policyNumber='" + policyNumber + '\'' +
                ", amountToPayout=" + amountToPayout +
                ", eventStatus=" + eventStatus +
                "} " + super.toString();
    }
}
