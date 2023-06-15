package fwdays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fwdays.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
