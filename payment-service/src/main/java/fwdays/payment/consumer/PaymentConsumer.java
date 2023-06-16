package fwdays.payment.consumer;


import fwdays.event.IntegrationEvent;
import fwdays.event.OrderCompletedEvent;
import fwdays.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "orders")
    void handle(@Payload IntegrationEvent event) {
        if (event instanceof OrderCompletedEvent completedEvent) {
            log.info("OrderCompletedEvent received: {}", completedEvent.getEntityId());
            paymentService.pay(completedEvent.getEntityId());
        }

    }
}
