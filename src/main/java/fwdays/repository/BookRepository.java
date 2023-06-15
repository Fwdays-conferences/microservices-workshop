package fwdays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fwdays.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
