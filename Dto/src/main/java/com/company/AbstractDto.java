package com.company;

import java.util.UUID;

public abstract class AbstractDto {
    private final UUID requestId;
    private Long version;

    public AbstractDto() {
        this.requestId = UUID.randomUUID();
    }

    public UUID getRequestId() {
        return requestId;
    }

    public Long getVersion() {
        return version;
    }
}
