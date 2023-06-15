package fwdays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fwdays.domain.Book;
import fwdays.domain.Customer;
import fwdays.repository.BookRepository;
import fwdays.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories("fwdays.repository")
public class MonolithApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonolithApplication.class, args);
    }

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void setup() {
        Book book = new Book();
        book.setName("Thinking in Java");
        book.setPages(1150);
        book.setYear(2006);
        bookRepository.save(book);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Sam Newman");
        customer.setEmail("sam.newman@gmail.com");
        customerRepository.save(customer);
    }
}