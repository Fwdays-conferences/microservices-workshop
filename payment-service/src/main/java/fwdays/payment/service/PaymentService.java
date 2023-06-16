package fwdays.payment.service;

import fwdays.event.IntegrationEvent;
import fwdays.event.OrderPayedEvent;
import fwdays.order.web.api.OrderFacade;
import fwdays.order.web.dto.OrderDTO;
import fwdays.payment.domain.Payment;
import fwdays.payment.persistence.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    //    private final OrderRepository orderRepository;
//
    private final PaymentGateway paymentGateway;
    //
//    private final NotificationService notificationService;
//
    private final PaymentRepository paymentRepository;

    private final OrderFacade orderFacade;

    private final KafkaTemplate<Integer, IntegrationEvent> kafkaTemplate;

    public void pay(int orderId) {
        //FIXME what is orderId invalid?
        OrderDTO orderDTO = orderFacade.findOne(orderId);

        Payment payment = paymentGateway.charge(orderDTO);

        paymentRepository.save(payment);

        OrderPayedEvent event = new OrderPayedEvent(orderId);
        kafkaTemplate.send("orders", orderId, event);

        log.info("OrderPayedEvent sent: {}", orderId);
    }
//
//    public void pay(Order order) {
//        Payment payment = paymentGateway.charge(order);
//
//        order.setPayed(true);
//        orderRepository.save(order);
//
//        paymentRepository.save(payment);
//
//        Notification notification = new Notification();
//        notification.setEmail(order.getCustomer().getEmail());
//        notification.setRecipient(order.getCustomer().getName());
//        notification.setTitle("Order " + order.getId() + " was payed");
//        notification.setText("Hi/n. Your order was payed successfully");
//
//        notificationService.sendNotification(notification);
//        System.out.println("Charging completed");
//    }

}
