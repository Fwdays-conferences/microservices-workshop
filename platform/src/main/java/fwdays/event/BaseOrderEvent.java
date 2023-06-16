package fwdays.event;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseOrderEvent implements IntegrationEvent {

    private int entityId;

    private String id;

    private LocalDateTime created;

    public BaseOrderEvent() {
    }

    public BaseOrderEvent(int orderId) {
        entityId = orderId;
        id = UUID.randomUUID().toString();
        created = LocalDateTime.now();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
    }
}
