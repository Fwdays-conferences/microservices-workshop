package fwdays.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCompletedEvent extends BaseOrderEvent {
    public OrderCompletedEvent(int orderId) {
        super(orderId);
    }

    public OrderCompletedEvent() {
    }
}
