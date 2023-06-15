package fwdays.delivery.service;

import fwdays.book.domain.Book;
import fwdays.book.persistence.BookRepository;
import fwdays.delivery.domain.Manager;
import fwdays.delivery.persistence.ManagerRepository;
import fwdays.notification.domain.Notification;
import fwdays.notification.service.NotificationService;
import fwdays.order.domain.Order;
import fwdays.order.domain.OrderItem;
import fwdays.order.persistence.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryService {
	private OrderRepository orderRepository;

	private BookRepository bookRepository;

	private ManagerRepository managerRepository;

	private NotificationService notificationService;

	public void deliver(Order order) {
		//TODO find all non-busy managers
		List<Manager> managers = managerRepository.findAll();
		Manager deliveryManager = managers.get(0);

		order.setDelivered(true);
		order.setDeliveryDate(LocalDateTime.now());
		order.setDeliveryManager(deliveryManager);

		orderRepository.save(order);

		for (OrderItem item : order.getItems()) {
			Book book = item.getBook();
			book.setAmount(book.getAmount() - item.getNumber());
			bookRepository.save(book);
		}

		Notification notification = new Notification();
		notification.setEmail(order.getCustomer().getEmail());
		notification.setRecipient(order.getCustomer().getName());
		notification.setTitle("Order " + order.getId() + " is delivered");
		notification.setText("Hi/n. Your order has been delivered");

		notificationService.sendNotification(notification);

		System.out.println("Order delivered!");
	}

}
