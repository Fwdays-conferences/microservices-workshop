package fwdays.order;

import fwdays.order.domain.Customer;
import fwdays.order.persistence.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    ApplicationListener<ContextRefreshedEvent> applicationListener(CustomerRepository customerRepository) {
        return (event) -> {
            Customer customer = new Customer();
            customer.setId(1);
            customer.setName("Sam Newman");
            customer.setEmail("sam.newman@gmail.com");
            customerRepository.save(customer);
        };
    }
}
