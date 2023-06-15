package fwdays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fwdays.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
