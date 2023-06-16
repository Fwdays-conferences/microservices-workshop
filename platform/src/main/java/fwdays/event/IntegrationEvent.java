package fwdays.event;

import java.time.LocalDateTime;

public interface IntegrationEvent {

    String getId();

    int getEntityId();

    LocalDateTime getCreated();
}
