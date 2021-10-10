package com.company.kafka;

import com.company.AbstractDto;

public class PremiumCreatedEvent extends AbstractDto {
    private String policyNumber;
    private EventStatus eventStatus;

    private PremiumCreatedEvent() {
    }

    public PremiumCreatedEvent(String policyNumber, EventStatus eventStatus) {
        this.policyNumber = policyNumber;
        this.eventStatus = eventStatus;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    @Override
    public String toString() {
        return "PremiumCreatedEvent{" +
                "policyNumber='" + policyNumber + '\'' +
                ", eventStatus=" + eventStatus +
                "} " + super.toString();
    }
}
