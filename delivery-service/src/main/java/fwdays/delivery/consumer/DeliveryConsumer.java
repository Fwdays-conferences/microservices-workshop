package fwdays.delivery.consumer;


import fwdays.delivery.service.DeliveryService;
import fwdays.event.IntegrationEvent;
import fwdays.event.OrderPayedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class DeliveryConsumer {

    private final DeliveryService deliveryService;

    @KafkaListener(topics = "orders")
    void handle(@Payload IntegrationEvent event) {
        if (event instanceof OrderPayedEvent payedEvent) {
            log.info("OrderPayedEvent received: {}", payedEvent.getEntityId());
            deliveryService.deliver(payedEvent.getEntityId());

        }

    }
}
