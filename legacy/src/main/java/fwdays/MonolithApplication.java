package fwdays;

import fwdays.book.domain.Book;
import fwdays.book.persistence.BookRepository;
import fwdays.config.LegacyPreferences;
import fwdays.order.domain.Customer;
import fwdays.order.persistence.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(LegacyPreferences.class)
public class MonolithApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonolithApplication.class, args);
    }

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

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