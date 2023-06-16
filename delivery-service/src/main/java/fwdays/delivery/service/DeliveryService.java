package fwdays.delivery.service;

import fwdays.delivery.domain.Manager;
import fwdays.delivery.domain.Order;
import fwdays.delivery.persistence.ManagerRepository;
import fwdays.delivery.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final OrderRepository orderRepository;
    //
//    private BookRepository bookRepository;
//
    private final ManagerRepository managerRepository;
//
//    private NotificationService notificationService;

    public void deliver(int orderId) {
        //TODO find all non-busy managers
        List<Manager> managers = managerRepository.findAll();
        Manager deliveryManager = managers.get(0);

        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setDelivered(true);
        order.setDeliveryDate(LocalDateTime.now());
        order.setDeliveryManager(deliveryManager);

        orderRepository.save(order);

//        for (OrderItem item : order.getItems()) {
//            Book book = item.getBook();
//            book.setAmount(book.getAmount() - item.getNumber());
//            bookRepository.save(book);
//        }
//
//        Notification notification = new Notification();
//        notification.setEmail(order.getCustomer().getEmail());
//        notification.setRecipient(order.getCustomer().getName());
//        notification.setTitle("Order " + order.getId() + " is delivered");
//        notification.setText("Hi/n. Your order has been delivered");
//
//        notificationService.sendNotification(notification);
//
        System.out.println("Order delivered!");
    }

}
