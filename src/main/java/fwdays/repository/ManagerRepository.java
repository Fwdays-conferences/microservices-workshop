package fwdays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fwdays.domain.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
