package fwdays.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCancelledEvent extends BaseOrderEvent {
    public OrderCancelledEvent(int orderId) {
        super(orderId);
    }
}
