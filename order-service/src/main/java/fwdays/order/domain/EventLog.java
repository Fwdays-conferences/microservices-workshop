package fwdays.order.domain;

import fwdays.order.event.sourcing.EventSourcingEvent;
import fwdays.order.event.sourcing.OrderCreatedEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "eventstore")
public class EventLog {

    @Id
    private String id;

    @Indexed
    private int entityId;

    private String type;

    @Indexed
    private LocalDateTime created;

    private int userId;

    private EventSourcingEvent payload;

    public EventLog(int entityId, String type, OrderCreatedEvent payload) {
        this.entityId = entityId;
        this.type = type;
        this.payload = payload;
    }
}
