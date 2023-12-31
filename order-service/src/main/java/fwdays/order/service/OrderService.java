package fwdays.order.service;

import fwdays.event.IntegrationEvent;
import fwdays.event.OrderCompletedEvent;
import fwdays.order.command.CreateOrderCommand;
import fwdays.order.command.handler.OrderCommandHandler;
import fwdays.order.domain.EventLog;
import fwdays.order.domain.Order;
import fwdays.order.domain.OrderItem;
import fwdays.order.event.sourcing.OrderCreatedEvent;
import fwdays.order.persistence.CustomerRepository;
import fwdays.order.persistence.EventLogRepository;
import fwdays.order.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    //
//	private final BookRepository bookRepository;
//
    private final CustomerRepository customerRepository;

    private final KafkaTemplate<Integer, IntegrationEvent> kafkaTemplate;

    private final EventLogRepository eventLogRepository;

    private final OrderCommandHandler orderCommandHandler;
//
//	private final NotificationService notificationService;
//
//	private final PaymentService paymentService;

    public void complete(int orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setCompleted(true);
            orderRepository.save(order);

            //TODO apply outbox pattern to support atomicity in dual write
            kafkaTemplate.send("orders", order.getId(), new OrderCompletedEvent(order.getId()));

//			paymentService.pay(order);

//			Notification notification = new Notification();
//			notification.setEmail(order.getCustomer().getEmail());
//			notification.setRecipient(order.getCustomer().getName());
//			notification.setTitle("Order " + order.getId() + " is completed");
//			notification.setText("Hi/n. Your order has been completed");
//
//			notificationService.sendNotification(notification);
        });
    }

    public void cancel(int orderId) {
//		orderRepository.findById(orderId).ifPresent(order -> {
//			if (order != null) {
//				order.setCancelled(true);
//				orderRepository.save(order);
//
//				Notification notification = new Notification();
//				notification.setEmail(order.getCustomer().getEmail());
//				notification.setRecipient(order.getCustomer().getName());
//				notification.setTitle("Order " + order.getId() + " is canceled");
//				notification.setText("Hi/n. Your order has been canceled");
//
//				notificationService.sendNotification(notification);
//
//			}
//		});
    }

    public int createOrder(int bookId, int number, int customerId, double price) {
        CreateOrderCommand command = new CreateOrderCommand(bookId, customerId, number, price);
//
//        Order order = new Order();
//        order.addItem(new OrderItem(bookId, number, price));
//        order.setCustomer(customerRepository.findById(customerId).orElseThrow());
//
//        orderRepository.save(order);

        return orderCommandHandler.handle(command);
    }

    public void addBook(int orderId, int bookId, int number, double price) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.addItem(new OrderItem(bookId, number, price));
            orderRepository.save(order);
        });
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(int orderId) {
        //return orderRepository.findById(orderId).orElseThrow();
        List<EventLog> logs = eventLogRepository.findByEntityIdOrderByCreatedAsc(orderId);
        if (logs.isEmpty()) {
            //TODO return Optional.empty()
            return null;
        }
        Order order = new Order();
        logs.stream().map(EventLog::getPayload)
                .forEach(event -> {
                    if (event instanceof OrderCreatedEvent createdEvent) {
                        order.apply(createdEvent);
                    }
                });

        return order;
    }

}
