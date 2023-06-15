package fwdays.order.service;

import fwdays.book.persistence.BookRepository;
import fwdays.notification.domain.Notification;
import fwdays.notification.service.NotificationService;
import fwdays.order.domain.Order;
import fwdays.order.domain.OrderItem;
import fwdays.order.persistence.CustomerRepository;
import fwdays.order.persistence.OrderRepository;
import fwdays.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	private final BookRepository bookRepository;

	private final CustomerRepository customerRepository;

	private final NotificationService notificationService;

	private final PaymentService paymentService;

	public void complete(int orderId) {
		orderRepository.findById(orderId).ifPresent(order -> {
			order.setCompleted(true);
			orderRepository.save(order);

			paymentService.pay(order);

			Notification notification = new Notification();
			notification.setEmail(order.getCustomer().getEmail());
			notification.setRecipient(order.getCustomer().getName());
			notification.setTitle("Order " + order.getId() + " is completed");
			notification.setText("Hi/n. Your order has been completed");

			notificationService.sendNotification(notification);
		});
	}

	public void cancel(int orderId) {
		orderRepository.findById(orderId).ifPresent(order -> {
			if (order != null) {
				order.setCancelled(true);
				orderRepository.save(order);

				Notification notification = new Notification();
				notification.setEmail(order.getCustomer().getEmail());
				notification.setRecipient(order.getCustomer().getName());
				notification.setTitle("Order " + order.getId() + " is canceled");
				notification.setText("Hi/n. Your order has been canceled");

				notificationService.sendNotification(notification);

			}
		});
	}

	public Order createOrder(int bookId, int number, int customerId) {
		Order order = new Order();
		order.addItem(new OrderItem(bookRepository.findById(bookId).orElseThrow(), number));
		order.setCustomer(customerRepository.findById(customerId).orElseThrow());

		orderRepository.save(order);

		return order;
	}

	public void addBook(int orderId, int bookId, int number) {
		orderRepository.findById(orderId).ifPresent(order -> {
			order.addItem(new OrderItem(bookRepository.findById(bookId).orElseThrow(), number));
			orderRepository.save(order);
		});
	}

	public List<Order> findOrders() {
		return orderRepository.findAll();
	}

	public Order findOrderById(int orderId) {
		return orderRepository.findById(orderId).orElseThrow();
	}

}
