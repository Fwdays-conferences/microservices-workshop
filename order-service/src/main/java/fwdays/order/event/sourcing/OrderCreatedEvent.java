package fwdays.order.event.sourcing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedEvent implements EventSourcingEvent {

    private int orderId;

    private int bookId;

    private int customerId;

    private int number;

    private double price;
}
