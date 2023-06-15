package fwdays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fwdays.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
