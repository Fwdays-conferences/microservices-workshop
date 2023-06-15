package fwdays.notification.service;

import fwdays.notification.domain.Notification;
import fwdays.notification.persistence.NotificationRepository;

public class NotificationService {
	private NotificationRepository notificationRepository;

	public void sendNotification(Notification notification) {
		System.out.println("Sending notification ... " + notification.toString());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notificationRepository.save(notification);

		System.out.println("Notification sent");
	}

}
