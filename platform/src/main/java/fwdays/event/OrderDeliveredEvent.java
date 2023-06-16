package fwdays.event;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDeliveredEvent extends BaseOrderEvent {
    public OrderDeliveredEvent(int orderId) {
        super(orderId);
    }
}
