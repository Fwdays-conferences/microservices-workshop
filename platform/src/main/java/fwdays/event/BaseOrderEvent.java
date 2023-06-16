package fwdays.event;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseOrderEvent implements IntegrationEvent {

    private final int orderId;

    private final String id;

    private final LocalDateTime created;

    public BaseOrderEvent(int orderId) {
        this.orderId = orderId;
        id = UUID.randomUUID().toString();
        created = LocalDateTime.now();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getEntityId() {
        return orderId;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
    }
}
