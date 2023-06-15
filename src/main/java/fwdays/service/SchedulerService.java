package fwdays.service;

import fwdays.domain.Notification;
import fwdays.repository.OrderRepository;

public class SchedulerService {
	private OrderRepository orderRepository;

	private NotificationService notificationService;

	private DeliveryService deliveryService;

	public void run() {
		notifyPendingOrders();
		startDeliveries();
	}

	private void startDeliveries() {
		while (true) {
			try {
				orderRepository.findAll().stream().filter(order -> order.isPayed() && !order.isDelivered())
						.forEach(order -> {
							deliveryService.deliver(order);
						});
				// Activate each 30 seconds
				Thread.sleep(30 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void notifyPendingOrders() {
		while (true) {
			try {
				orderRepository.findAll().stream().filter(order -> !order.isPayed()).forEach(order -> {
					Notification notification = new Notification();
					notification.setEmail(order.getCustomer().getEmail());
					notification.setRecipient(order.getCustomer().getName());
					notification.setTitle("Don't forget to pay order " + order.getId());
					notification.setText("Please, pay your outstaning order " + order.getId());

					notificationService.sendNotification(notification);
				});
				// Activate each 30 seconds
				Thread.sleep(30 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
