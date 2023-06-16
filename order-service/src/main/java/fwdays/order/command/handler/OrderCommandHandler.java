package fwdays.order.command.handler;

import fwdays.order.command.CreateOrderCommand;
import fwdays.order.domain.EventLog;
import fwdays.order.event.sourcing.OrderCreatedEvent;
import fwdays.order.persistence.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCommandHandler {

    private final EventLogRepository eventLogRepository;

    public int handle(CreateOrderCommand cmd) {
        OrderCreatedEvent event = new OrderCreatedEvent();

        eventLogRepository.save(new EventLog(event.getOrderId(), event.getClass().getName(), event));
        return event.getOrderId();
    }
}
