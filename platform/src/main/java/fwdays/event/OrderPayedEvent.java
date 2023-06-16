package fwdays.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPayedEvent extends BaseOrderEvent {
    public OrderPayedEvent(int orderId) {
        super(orderId);
    }

    public OrderPayedEvent() {
    }
}
